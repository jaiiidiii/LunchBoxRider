package com.jayzonsolutions.lunchboxrider;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jayzonsolutions.lunchboxrider.Service.OrderService;
import com.jayzonsolutions.lunchboxrider.model.ApiResponse;
import com.jayzonsolutions.lunchboxrider.model.Order;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToPickActivity extends AppCompatActivity {

    List<Order> orderList;
    RecyclerView recyclerView;
    RecycleAdapter recyclerAdapter;
    private OrderService orderService;
    Context context = ToPickActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acknowledged_request);
        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerAdapter = new RecycleAdapter(ToPickActivity.this, orderList);


        orderService = ApiUtils.getOrderService();
        orderService.riderOrders(1,4).enqueue(new Callback<List<Order>>() {

            @Override
            public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {


                orderList = response.body();
                if(orderList == null){
                    orderList = new ArrayList<Order>();
                }
                recyclerAdapter.setCustomerOrderList(orderList);

            }

            @Override
            public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {
                Toast.makeText(context, "connection problem", Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(recyclerAdapter);
    }





    private int dpToPx() {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, r.getDisplayMetrics()));
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }



    public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

        Context context;
        boolean showingFirst = true;
        int recentPos = -1;
        private List customerOrderList;


        RecycleAdapter(Context context, List customerOrderList) {
            if(customerOrderList == null){
                customerOrderList = new ArrayList<Order>();
            }
            this.customerOrderList = customerOrderList;
            this.context = context;
        }

        void setCustomerOrderList(List customerOrderList) {
            if(customerOrderList == null){
                customerOrderList = new ArrayList<Order>();

            }

            this.customerOrderList = customerOrderList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);


            return new RecycleAdapter.MyViewHolder(itemView);


        }

        void openMap(int pos)
        {
            String lat_long = orderList.get(pos).getLatitude() + "," + orderList.get(pos).getLongitude();

            Uri gmmIntentUri = Uri.parse("google.navigation:q="+lat_long);
// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
// Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
            }

        }

        @SuppressLint("SetTextI18n")
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(@NonNull final RecycleAdapter.MyViewHolder holder, final int position) {
            holder.title.setText(""+orderList.get(position).getFoodmaker().getFoodmakerName());
            holder.price.setText(""+orderList.get(position).getOrderTotalAmount());
            holder.quantity = 1;
           /* holder.title.setText("Dummy Title");
            holder.price.setText("Dummy Price");
            holder.quantity = 3;
*/

            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(View v, final int pos) {

                    Toast.makeText(context, "Clicked Position =" + pos, Toast.LENGTH_SHORT).show();

                    // Create a Uri from an intent string. Use the result to create an Intent.
                    //        Uri gmmIntentUri = Uri.parse("geo:24.841998,67.081242?z=20");


                    final AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Complete Order");
                    alert.setMessage("Are you want to show Order Recipe?");
                    alert.setPositiveButton("Show Recipe", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            BillingDetails.setOrderId(orderList.get(position).getOrderId());
                            Intent in = new Intent(ToPickActivity.this,BillingDetails.class);
                            startActivity(in);
                        }
                    });
                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           dialog.dismiss();
                        }
                    });

                    alert.show();
                }
            });
        }
        @Override
        public int getItemCount() {
            return customerOrderList.size();
        }


        public void removeAt(int position) {
            customerOrderList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, customerOrderList.size());
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            ImageView image;
            TextView title;
            TextView price;

            int quantity;

            private ItemClickListener itemClickListener;

            MyViewHolder(View view) {
                super(view);

                image = view.findViewById(R.id.imageProduct1);
                title = view.findViewById(R.id.titleProduct1);
                price = view.findViewById(R.id.price1);
                view.setOnClickListener(this);
            }
            @Override
            public void onClick(View v) {
                this.itemClickListener.onItemClick(v, getLayoutPosition());
            }

            void setItemClickListener(ItemClickListener ic) {
                this.itemClickListener = ic;

            }
        }

    }
 /*   @Override
    public void onResume() {

        super.onResume();


        orderService.getAckOrdersBycustomerId(13).enqueue(new Callback<List<Order>>() {

            @Override
            public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {


                    orderList = response.body();
                    if(orderList == null){
                        orderList = new ArrayList<Order>();
                    }else{
                        Log.d("TAG", "Response = " + orderList);
                        recyclerAdapter.setCustomerOrderList(orderList);
                    }



            }

            @Override
            public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {
                Toast.makeText(AcknowledgedRequestActivity.this, "connection problem", Toast.LENGTH_LONG).show();
            }
        });

    }*/

}
