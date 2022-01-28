package apps.sami.wallpapev1.Models;

public class WallpaperModel {
    private int id;
    private String originalUrl,large2xUrl,largeUrl,mediumUrl,smallUrl,portraitUrl,landscape,tinyUrl;

    public WallpaperModel() {
    }

    public WallpaperModel(int id, String originalUrl, String large2xUrl, String largeUrl, String mediumUrl, String smallUrl, String portraitUrl, String landscape, String tinyUrl) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.large2xUrl = large2xUrl;
        this.largeUrl = largeUrl;
        this.mediumUrl = mediumUrl;
        this.smallUrl = smallUrl;
        this.portraitUrl = portraitUrl;
        this.landscape = landscape;
        this.tinyUrl = tinyUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getLarge2xUrl() {
        return large2xUrl;
    }

    public void setLarge2xUrl(String large2xUrl) {
        this.large2xUrl = large2xUrl;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public String getMediumUrl() {
        return mediumUrl;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }

    public String getTinyUrl() {
        return tinyUrl;
    }

    public void setTinyUrl(String tinyUrl) {
        this.tinyUrl = tinyUrl;
    }
}
