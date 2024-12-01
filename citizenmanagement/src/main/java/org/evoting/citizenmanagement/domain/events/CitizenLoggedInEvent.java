package org.evoting.citizenmanagement.domain.events;

public class CitizenLoggedInEvent {

        private final String email;
        private final String password;

        public CitizenLoggedInEvent( String email,String password) {
            this.email = email;
            this.password = password;
        }


        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

}
