package com.example.elif.eliff;


public class kullanıcı {

        private String isim,mail,password;
        public kullanıcı() {}

        public kullanıcı(String isim, String mail, String password) {
            this.isim = isim;
            this.mail = mail;
            this.password = password;
        }

        public String getIsim() {
            return isim;
        }

        public void setIsim(String isim) {
            this.isim = isim;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

