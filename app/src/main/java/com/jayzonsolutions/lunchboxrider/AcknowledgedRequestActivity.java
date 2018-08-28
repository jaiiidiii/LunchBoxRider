package com.jayzonsolutions.lunchboxrider;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
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

import java.util.List;

public class AcknowledgedRequestActivity extends AppCompatActivity {

    List orderList;
    RecyclerView recyclerView;
    RecycleAdapter recyclerAdapter;
    Context context = AcknowledgedRequestActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acknowledged_request);
        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerAdapter = new RecycleAdapter(AcknowledgedRequestActivity.this, orderList);
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
            this.customerOrderList = customerOrderList;
            this.context = context;
        }

        void setCustomerOrderList(List customerOrderList) {
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

        @SuppressLint("SetTextI18n")
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(@NonNull final RecycleAdapter.MyViewHolder holder, final int position) {
            /*  holder.title.setText(customerOrderList.get(position).getFoodmaker().getFoodmakerName());
                holder.price.setText(customerOrderList.get(position).getOrderTotalAmount().toString());
                holder.quantity = customerOrderList.get(position).getOrderdishes().size();
*/
            holder.title.setText("Dummy Title");
            holder.price.setText("Dummy Price");
            holder.quantity = 3;


            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(View v, final int pos) {

                    Toast.makeText(context, "Clicked Position =" + pos, Toast.LENGTH_SHORT).show();


                    /*
                    Log.d("pos", String.valueOf(pos));
                    //     Toast.makeText(context, "Clicked Position =" + pos, Toast.LENGTH_SHORT).show();
                    final ScrollView s_view = new ScrollView(context);
                    final TextView t_view = new TextView(context);

                    SpannableString DishName = new SpannableString("> Dish: \n");
                    DishName.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, DishName.length(), 0);

                    SpannableString DishDescription = new SpannableString("> Dishes:\n");
                    DishDescription.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, DishDescription.length(), 0);
                    t_view.append(DishDescription);
                    for(int i = 0; i < customerOrderList.get(pos).getOrderdishes().size(); i++ )
                    {



                        //  t_view.append(DishName);
                        //  t_view.append(customerOrderList.get(pos).getOrderdishes().get(i).getDishes().getName()+ "\n");

                        t_view.append(""+customerOrderList.get(pos).getOrderdishes().get(i).getDishes().getName()+ "\n");




                    }
                    t_view.setTextSize(15);
                    //  t_view.setTextColor(primary_text_material_dark);
                    s_view.addView(t_view);

//                    Toast.makeText(context, "Pressed Order Item", Toast.LENGTH_SHORT).show();
                    final AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Done Order");
                    // alert.setMessage(customerOrderList.get(pos).getOrderdishes().get(pos).getDishes().getDescription());
                    alert.setView(s_view);
                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(context, "Clicked Yes", Toast.LENGTH_SHORT).show();

                            //     setOrderStatus(foodmakerOrderList.get(pos).getOrderId());


                        }
                    });
                    alert.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //  Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show();

                            AlertDialog.Builder alert2 = new AlertDialog.Builder(context);
                            alert2.setTitle("Are You sure to cancel the order");
                            alert2.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    //   Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show();

                                    //     setOrderStatus(foodmakerOrderList.get(pos).getOrderId());
                                    orderService.updateOrderStatus(4, customerOrderList.get(pos).getOrderId()).enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                                            Toast.makeText(context, "Order status changed to 4", Toast.LENGTH_LONG).show();
                                            removeAt(pos);

                                        }

                                        @Override
                                        public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                                            Toast.makeText(context, "Response Failed", Toast.LENGTH_SHORT).show();
                                            //  Log.d("TAG", "failed");
                                        }
                                    });

                                }
                            });
                            alert2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(context, "Clicked No", Toast.LENGTH_SHORT).show();

                                }
                            });
                            alert2.show();
                        }
                    });
                 *//*   alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "Clicked No", Toast.LENGTH_SHORT).show();

                        }
                    });*//*

                    alert.show();
*/

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

}
