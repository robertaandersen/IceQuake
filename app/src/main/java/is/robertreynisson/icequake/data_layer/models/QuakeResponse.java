package is.robertreynisson.icequake.data_layer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class QuakeResponse {

    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }


    @Generated("org.jsonschema2pojo")
    public class Result {

        @SerializedName("timestamp")
        @Expose
        private String timestamp;
        @SerializedName("latitude")
        @Expose
        private Double latitude;
        @SerializedName("longitude")
        @Expose
        private Double longitude;
        @SerializedName("depth")
        @Expose
        private Double depth;
        @SerializedName("size")
        @Expose
        private Double size;
        @SerializedName("quality")
        @Expose
        private Double quality;
        @SerializedName("humanReadableLocation")
        @Expose
        private String humanReadableLocation;

        /**
         * @return The timestamp
         */
        public String getTimestamp() {
            return timestamp;
        }

        /**
         * @param timestamp The timestamp
         */
        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        /**
         * @return The latitude
         */
        public Double getLatitude() {
            return latitude;
        }

        /**
         * @param latitude The latitude
         */
        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        /**
         * @return The longitude
         */
        public Double getLongitude() {
            return longitude;
        }

        /**
         * @param longitude The longitude
         */
        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        /**
         * @return The depth
         */
        public Double getDepth() {
            return depth;
        }

        /**
         * @param depth The depth
         */
        public void setDepth(Double depth) {
            this.depth = depth;
        }

        /**
         * @return The size
         */
        public Double getSize() {
            return size;
        }

        /**
         * @param size The size
         */
        public void setSize(Double size) {
            this.size = size;
        }

        /**
         * @return The quality
         */
        public Double getQuality() {
            return quality;
        }

        /**
         * @param quality The quality
         */
        public void setQuality(Double quality) {
            this.quality = quality;
        }

        /**
         * @return The humanReadableLocation
         */
        public String getHumanReadableLocation() {
            return humanReadableLocation;
        }

        /**
         * @param humanReadableLocation The humanReadableLocation
         */
        public void setHumanReadableLocation(String humanReadableLocation) {
            this.humanReadableLocation = humanReadableLocation;
        }

    }
}
