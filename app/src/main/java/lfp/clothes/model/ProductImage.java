
package lfp.clothes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductImage implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("default")
    @Expose
    private String _default;
    @SerializedName("portrait")
    @Expose
    private String portrait;
    @SerializedName("landscape")
    @Expose
    private String landscape;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The _default
     */
    public String getDefault() {
        return _default;
    }

    /**
     * 
     * @param _default
     *     The default
     */
    public void setDefault(String _default) {
        this._default = _default;
    }

    /**
     * 
     * @return
     *     The portrait
     */
    public String getPortrait() {
        return portrait;
    }

    /**
     * 
     * @param portrait
     *     The portrait
     */
    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    /**
     * 
     * @return
     *     The landscape
     */
    public String getLandscape() {
        return landscape;
    }

    /**
     * 
     * @param landscape
     *     The landscape
     */
    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }

    /**
     * 
     * @return
     *     The thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
