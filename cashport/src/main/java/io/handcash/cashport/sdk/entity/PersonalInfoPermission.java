package io.handcash.cashport.sdk.entity;

public enum PersonalInfoPermission {
    HANDLE( "handle" );

    private String queryName;

    PersonalInfoPermission(String queryName) {
        this.queryName = queryName;
    }

    public String getQueryName() {
        return queryName;
    }

    public static PersonalInfoPermission getFromQueryName(String name) {
        for ( PersonalInfoPermission permission : PersonalInfoPermission.values() ) {
            if ( permission.getQueryName().equals( name ) ) {
                return permission;
            }
        }
        throw new IllegalArgumentException( "Unknown query name: " + name );
    }
}
