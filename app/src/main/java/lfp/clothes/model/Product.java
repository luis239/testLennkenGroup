
package lfp.clothes.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("brief")
    @Expose
    private String brief;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("featured")
    @Expose
    private Boolean featured;
    @SerializedName("sizes")
    @Expose
    private List<Size> sizes = new ArrayList<Size>();
    @SerializedName("product_image")
    @Expose
    private ProductImage productImage;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("product_gallery_images")
    @Expose
    private List<Object> productGalleryImages = new ArrayList<Object>();

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
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     *     The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     *     The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency
     *     The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     *
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     *     The brief
     */
    public String getBrief() {
        return brief;
    }

    /**
     *
     * @param brief
     *     The brief
     */
    public void setBrief(String brief) {
        this.brief = brief;
    }

    /**
     *
     * @return
     *     The code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     *     The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     *     The model
     */
    public String getModel() {
        return model;
    }

    /**
     *
     * @param model
     *     The model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     *
     * @return
     *     The featured
     */
    public Boolean getFeatured() {
        return featured;
    }

    /**
     *
     * @param featured
     *     The featured
     */
    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    /**
     *
     * @return
     *     The sizes
     */
    public List<Size> getSizes() {
        return sizes;
    }

    /**
     *
     * @param sizes
     *     The sizes
     */
    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    /**
     *
     * @return
     *     The productImage
     */
    public ProductImage getProductImage() {
        return productImage;
    }

    /**
     *
     * @param productImage
     *     The product_image
     */
    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }

    /**
     *
     * @return
     *     The views
     */
    public Integer getViews() {
        return views;
    }

    /**
     *
     * @param views
     *     The views
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     *
     * @return
     *     The productGalleryImages
     */
    public List<Object> getProductGalleryImages() {
        return productGalleryImages;
    }

    /**
     *
     * @param productGalleryImages
     *     The product_gallery_images
     */
    public void setProductGalleryImages(List<Object> productGalleryImages) {
        this.productGalleryImages = productGalleryImages;
    }


    protected Product(Parcel in) {
        id = in.readString();
        url = in.readString();
        name = in.readString();
        price = in.readString();
        currency = in.readString();
        description = in.readString();
        brief = in.readString();
        code = in.readString();
        model = in.readString();
        byte featuredVal = in.readByte();
        featured = featuredVal == 0x02 ? null : featuredVal != 0x00;
        if (in.readByte() == 0x01) {
            sizes = new ArrayList<Size>();
            in.readList(sizes, Size.class.getClassLoader());
        } else {
            sizes = null;
        }
        productImage = (ProductImage) in.readValue(ProductImage.class.getClassLoader());
        views = in.readByte() == 0x00 ? null : in.readInt();
        if (in.readByte() == 0x01) {
            productGalleryImages = new ArrayList<Object>();
            in.readList(productGalleryImages, Object.class.getClassLoader());
        } else {
            productGalleryImages = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(url);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(currency);
        dest.writeString(description);
        dest.writeString(brief);
        dest.writeString(code);
        dest.writeString(model);
        if (featured == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (featured ? 0x01 : 0x00));
        }
        if (sizes == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(sizes);
        }
        dest.writeValue(productImage);
        if (views == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(views);
        }
        if (productGalleryImages == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(productGalleryImages);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}