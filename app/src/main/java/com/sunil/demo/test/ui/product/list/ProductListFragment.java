package com.sunil.demo.test.ui.product.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunil.demo.test.AppController;
import com.sunil.demo.test.R;
import com.sunil.demo.test.entity.Product;
import com.sunil.demo.test.injection.ApplicationFactory;
import com.sunil.demo.test.ui.product.add.AddProductActivity;
import com.sunil.demo.test.ui.product.photo.PhotoActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListFragment extends Fragment {

    private static final String TAG = "ProductListFragment";
    private ProductAdapter adapter;
    private ProductListViewModel productListViewModel;

    @BindView(R.id.recycler_view_list_product)
    RecyclerView recyclerView;

    private View.OnClickListener deleteClickListener = v -> {
        Product product = (Product) v.getTag();
        productListViewModel.deleteProduct(product);
    };

    private View.OnClickListener editClickListener = v -> {
        Product product = (Product) v.getTag();
        Intent intent = new Intent(getActivity(), AddProductActivity.class);
        intent.putExtra(AddProductActivity.PRODUCT, product);
        startActivity(intent);
    };

    private View.OnClickListener photoClickListener = v -> {
        Product product = (Product) v.getTag();
        Intent intent = new Intent(getActivity(), PhotoActivity.class);
        intent.putExtra("photo", product.getProduct_photo());
        startActivity(intent);
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product_list, container, false);
        setupRecyclerView(v);

        AppController application = (AppController) getActivity().getApplication();
        productListViewModel = ViewModelProviders.of(this, new ApplicationFactory(application)).get(ProductListViewModel.class);


        productListViewModel.getProducts().observe(this, products -> {
            Log.d(TAG, "Product Changed:" + products);
            adapter.setItems(products);

            if(products.size() == 0) {
                productListViewModel.setMockData(true);
            }
        });

        return v;
    }

    private void setupRecyclerView(View view) {
        ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ProductAdapter(new ArrayList<>(), getContext(), photoClickListener, deleteClickListener, editClickListener);
        recyclerView.setAdapter(adapter);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.transparent)));

    }
}
