package vn.edu.ntu.vohuuhuy.nav;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

import vn.edu.ntu.vohuuhuy.nav.R;
import vn.edu.ntu.vohuuhuy.nav.model.Product;
import vn.edu.ntu.vohuuhuy.nav.controller.ICartController;

public class ListProductFragment extends Fragment {
    FloatingActionButton fab;
    RecyclerView rvMatHang;
    RecyclerView.Adapter product;
    ICartController cartController;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.mnu_cart,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_cart:
                NavHostFragment.findNavController(ListProductFragment.this).navigate(R.id.action_listProductFragment_to_shoppingCartFragment);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_list_product, container, false);
        fab = view.findViewById(R.id.fab);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMatHang = getView().findViewById(R.id.rvListProduct);
        rvMatHang.setLayoutManager(new LinearLayoutManager(getActivity()));
        cartController = ((MainActivity)getActivity()).cartController;
        product = new Adapter(cartController.getAllProduct());
        rvMatHang.setAdapter(product);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller;
                NavHostFragment.findNavController(ListProductFragment.this)
                        .navigate(R.id.action_listProductFragment_to_productFragment);
            }
        });
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView txtName ,txtPrice, txtDesc;
        ImageView imvAddToCart;
        Product product;

        public ProductViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtNameProduct);
            txtPrice = this.itemView.findViewById(R.id.txtPrice);
            txtDesc = this.itemView.findViewById(R.id.txtDesc);
            imvAddToCart  = this.itemView.findViewById(R.id.imvAddToCart);//this phuong thuc dang viet tai day: (ProductViewHolder)
            imvAddToCart.setOnClickListener(this);//this phuong thuc dang viet tai day.(onClickListener)

        }

        public void bind (Product p)
        {
            this.product = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDesc.setText(p.getDesc());

        }

        @Override
        public void onClick(View v) {
            if (cartController.addToCart(product)) {
                Toast.makeText(getActivity(),
                        "Đã thêm: " + product.getName() + " vào giỏ hàng.",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(getActivity(),
                        product.getName() + " đã có trong giỏ hàng.",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }


    }

    private class Adapter extends RecyclerView.Adapter<ProductViewHolder>
    {
        List<Product> productList;

        public Adapter(List<Product> productList) {
            this.productList = productList;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind(this.productList.get(position));
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }
    }

}
