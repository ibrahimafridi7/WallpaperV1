package apps.sami.wallpapev1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import apps.sami.wallpapev1.FullScreenActivity;
import apps.sami.wallpapev1.Models.WallpaperModel;
import apps.sami.wallpapev1.R;

public class AdapterRVWallpaper extends RecyclerView.Adapter<WallpaperViewHolder> {

    private Context context;
    private List<WallpaperModel> wallpaperModelList;

    public AdapterRVWallpaper(Context context, List<WallpaperModel> wallpaperModelList) {
        this.context = context;
        this.wallpaperModelList = wallpaperModelList;
    }

    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wallpaperitem , parent , false);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {


        Glide.with(context).load(wallpaperModelList.get(position).getMediumUrl()).into(holder.imageView1);
        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, FullScreenActivity.class)
                .putExtra("originalUrl",wallpaperModelList.get(position).getOriginalUrl()));
            }
        });
    }

    @Override
    public int getItemCount() {

        return wallpaperModelList.size();
    }
}
class WallpaperViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView1;

    public WallpaperViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView1 = itemView.findViewById(R.id.imageViewItems1);
    }
}


