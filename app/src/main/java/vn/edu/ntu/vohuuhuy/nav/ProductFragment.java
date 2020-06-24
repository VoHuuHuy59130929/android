package vn.edu.ntu.vohuuhuy.nav;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.fragment.NavHostFragment;

import vn.edu.ntu.vohuuhuy.nav.R;
import vn.edu.ntu.vohuuhuy.nav.model.Product;
import vn.edu.ntu.vohuuhuy.nav.controller.ICartController;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {
    EditText txtPriceNew, txtNameProductNew, txtDescNew;
    Button btnAddProduct;
    ICartController cartController;
    public ProductFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_buy,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_pre_main:
                NavHostFragment.findNavController(ProductFragment.this).navigate(R.id.action_productFragment_to_listProductFragment);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtPriceNew = (EditText) getActivity().findViewById(R.id.txtPriceNew);
        txtNameProductNew = (EditText) getActivity().findViewById(R.id.txtNameProductNew);
        txtDescNew = (EditText) getActivity().findViewById(R.id.txtDescNew);
        btnAddProduct = (Button) getActivity().findViewById(R.id.btnAddProduct);
        cartController = ((MainActivity)getActivity()).cartController;
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product(txtNameProductNew.getText().toString(), Integer.parseInt(String.valueOf(txtPriceNew.getText())), txtDescNew.getText().toString());
                cartController.addProduct(product);
                NavHostFragment.findNavController(ProductFragment.this)
                        .navigate(R.id.action_productFragment_to_listProductFragment);
            }
        });
    }
}
