package com.gaoyy.latte.ec.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.gaoyy.latte.delegates.LatteDelegate;
import com.gaoyy.latte.ec.R;
import com.gaoyy.latte.ec.R2;
import com.gaoyy.latte.net.RestClient;
import com.gaoyy.latte.net.callback.ISuccess;
import com.gaoyy.latte.ui.banner.HolderCreator;
import com.gaoyy.latte.ui.widget.CircleTextView;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by gaoyy on 2018/1/18 0018.
 */

public class GoodsDetailDelegate extends LatteDelegate implements AppBarLayout.OnOffsetChangedListener
{

    @BindView(R2.id.goods_detail_toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.tab_layout)
    TabLayout mTabLayout = null;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager = null;
    @BindView(R2.id.detail_banner)
    ConvenientBanner<String> mBanner = null;
    @BindView(R2.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout mCollapsingToolbarLayout = null;
    @BindView(R2.id.app_bar_detail)
    AppBarLayout mAppBar = null;

    //底部
    @BindView(R2.id.icon_favor)
    IconTextView mIconFavor = null;
    @BindView(R2.id.tv_shopping_cart_amount)
    CircleTextView mCircleTextView = null;
    @BindView(R2.id.rl_add_shop_cart)
    RelativeLayout mRlAddShopCart = null;
    @BindView(R2.id.icon_shop_cart)
    IconTextView mIconShopCart = null;

    private static final String ARG_GOODS_ID = "ARG_GOODS_ID";
    private int mGoodsId = -1;


    public static GoodsDetailDelegate create(int goodsId)
    {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailDelegate delegate = new GoodsDetailDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null)
        {
            mGoodsId = args.getInt(ARG_GOODS_ID);
        }
    }

    @Override
    public Object setLayout()
    {
        return R.layout.delegate_goods_detail;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        mCollapsingToolbarLayout.setContentScrimColor(Color.WHITE);
        mAppBar.addOnOffsetChangedListener(this);
        initData();
    }

    private void initData()
    {
        RestClient.builder()
                .url("http://192.168.1.101/RestServer/api/goods_detail.php")
                .params("goods_id", mGoodsId)
                .loader(getContext())
                .success(new ISuccess()
                {
                    @Override
                    public void onSuccess(String response)
                    {
                        final JSONObject data =
                                JSON.parseObject(response).getJSONObject("data");
                        initBanner(data);
                    }
                })
                .build()
                .get();
    }

    private void initBanner(JSONObject data)
    {
        final JSONArray array = data.getJSONArray("banners");
        final List<String> images = new ArrayList<>();
        final int size = array.size();
        for (int i = 0; i < size; i++)
        {
            images.add(array.getString(i));
        }
        mBanner
                .setPages(new HolderCreator(), images)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_forcus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator()
    {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
    {

    }
}
