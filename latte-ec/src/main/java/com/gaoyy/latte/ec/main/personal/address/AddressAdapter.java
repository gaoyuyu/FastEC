package com.gaoyy.latte.ec.main.personal.address;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.gaoyy.latte.ec.R;
import com.gaoyy.latte.net.RestClient;
import com.gaoyy.latte.net.callback.ISuccess;
import com.gaoyy.latte.ui.recycler.MultipleFields;
import com.gaoyy.latte.ui.recycler.MultipleItemEntity;
import com.gaoyy.latte.ui.recycler.MultipleRecyclerAdapter;
import com.gaoyy.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by gaoyy on 2018/1/28 0028.
 */

public class AddressAdapter extends MultipleRecyclerAdapter
{
    protected AddressAdapter(List<MultipleItemEntity> data)
    {
        super(data);
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, MultipleItemEntity entity)
    {
        super.convert(holder, entity);
        switch (holder.getItemViewType())
        {
            case AddressItemType.ITEM_ADDRESS:
                final String name = entity.getField(MultipleFields.NAME);
                final String phone = entity.getField(AddressItemFields.PHONE);
                final String address = entity.getField(AddressItemFields.ADDRESS);
                final boolean isDefault = entity.getField(MultipleFields.TAG);
                final int id = entity.getField(MultipleFields.ID);

                final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
                final AppCompatTextView deleteTextView = holder.getView(R.id.tv_address_delete);
                deleteTextView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        RestClient.builder()
                                .url("http://192.168.1.101/RestServer/api/address.php")
                                .params("id", id)
                                .success(new ISuccess()
                                {
                                    @Override
                                    public void onSuccess(String response)
                                    {
                                        remove(holder.getLayoutPosition());
                                    }
                                })
                                .build()
                                .post();
                    }
                });

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);
                break;
            default:
                break;
        }
    }
}
