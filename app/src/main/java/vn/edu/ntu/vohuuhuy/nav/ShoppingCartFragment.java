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
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.fragment.NavHostFragment;

import vn.edu.ntu.vohuuhuy.nav.model.Product;
import vn.edu.ntu.vohuuhuy.nav.controller.ICartController;

public class ShoppingCartFragment extends Fragment {
    TextView txtShoppingCart;
    ICartController controller;
    Button btnOK, btnDelete;

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
                NavHostFragment.findNavController(ShoppingCartFragment.this).navigate(R.id.action_shoppingCartFragment_to_listProductFragment);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = (ICartController) getActivity().getApplication();
        addViews();
    }

    private void addViews() {
        txtShoppingCart = getActivity().findViewById(R.id.txtCart);
        displayShoppingCart();
    }

    private void displayShoppingCart() {
        StringBuilder builder = new StringBuilder();
        for (Product p: controller.getShoppingCart()){
            builder.append(p.getName())
                    .append("\t\t\t")
                    .append(p.getPrice())
                    .append("VND\n");
        }

        if (builder.toString().length() > 0)
        {
            txtShoppingCart.setText(builder.toString());
            btnOK = getActivity().findViewById(R.id.btnOK);
            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    controller.clearShoppingCart();
                    Toast toast = Toast.makeText(getActivity(), "Chung toi se gui hang cho ban trong vong 4 ngay", Toast.LENGTH_SHORT);
                    toast.show();
                    displayShoppingCart();
                }
            });
            btnDelete = getActivity().findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    controller.clearShoppingCart();
                    displayShoppingCart();
                }
            });
        }
        else
            txtShoppingCart.setText("Không có mặt hàng nào trong giỏ hàng.");
    }
}
