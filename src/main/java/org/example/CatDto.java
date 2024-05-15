package org.example;

public class CatDto {
    private String id;
    private  String pvtKey = "live_CCBPdTTXHVdlrzOWD3wbOYkrXvaec5FetvZNEllav6CkyylHiJajUQ6u3XGdvT4X";
    private  String url;
    private  String image;

    public CatDto() {
        this.pvtKey = "live_CCBPdTTXHVdlrzOWD3wbOYkrXvaec5FetvZNEllav6CkyylHiJajUQ6u3XGdvT4X";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPvtKey() {
        return pvtKey;
    }

    public void setPvtKey(String pvtKey) {
        this.pvtKey = pvtKey;
    }

    public String getUrl() {
        return url;
    }

    public void settUrl(String caturl) {
        this.url = caturl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "CatDto{" +
                "id='" + id + '\'' +
                ", caturl='" +
                url + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
