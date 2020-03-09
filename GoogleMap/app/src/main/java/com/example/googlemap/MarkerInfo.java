package com.example.googlemap;

public class MarkerInfo {



        private String name;
        private String type;
        private float lat;
        private float longt;
    private String category;
    private String website;
    byte[] photo;

        public MarkerInfo(String name, String type,float lat,float longt, String category,String website,byte[] photo){

            this.name=name;
            this.type=type;
            this.lat=lat;
            this.longt=longt;
            this.category=category;
            this.website = website;
            this.photo = photo;

        }

        public String getName() {
            return name;
        }
        public String getType() {
            return type;
        }
        public float getlat() {
        return lat;
    }
        public float getlongt() {
        return longt;
    }
        public String getCatefory() {
        return category;
    }
        public String getWebsite() {
        return website;
    }
        public byte[] getPhoto(){return photo;
        }





}
