package com.example.demoauthorization.utils;

public class Const {
    public static final String ROLE_USERMANAGER = "UserManager";
    public static final String ROLE_AREAMANAGER = "AreaManager";
    public static final String ROLE_SYSADMIN = "SysAdmin";
    public static final String ROLE_SALEMEMBER = "SaleMember";
    public static final String ROLE_OTHER = "Other";
    public static final long ROLE_TENANT = 20;
    public static final String MERCHANTID_COMMON = "OP_VINHOMES";

    public static final String PREFIX_ANNOUNCEMENT = "CA_";
    public static final String PREFIX_ANNOUNCEMENT_TOPIC = "CAT_";
    public static final String FIREBASE_SEPARATOR = "%";

    public static final String XLSX_EXTENSION = "xlsx";

    public static final String APP_USER_AUTHORIZATION = "APP_USER_AUTHORIZATION";

    public static final String TEMPLATE_LOCATION_PATH = "classpath:template/";

    public final class IMPORT {
        public static final String HEADER_APPLICATION_KEY = "Content-type";
        public static final String HEADER_APPLICATION_VALUE = "application/octet-stream";
        public static final String HEADER_FILE_KEY = "Content-disposition";
        public static final String HEADER_FILE_VALUE = "attachment; filename=";

        public static final String FILE_TYPE = ".xlsx";
        public static final int FILE_SIZE_IN_MB = 1048576;
        public static final int MAX_FILE_SIZE_IN_MB = 20;
    }

    public final class REDIS_REGISTRY_KEY {
        public static final String MENU_CODES_FOR_RESIDENT = "get-menu-codes-app-for-resident";

        public static final String SYSTEM_USER_AUTHORIZATION = "SYSTEM_USER_";
    }

    final class IMPORT_ANNOUNCEMENT_AREA {
        int AREA_NAME = 1;
        int APARTMENT_CODE = 2;
    }

    public final class CONTENT_ANNOUNCEMENT {
        public static final String FILE_TEMPLATE_CONTENT_ANNOUNCEMENT_AREAS = "TEMPLATE_CONTENT_ANNOUNCEMENT_AREA_IMPORT.xlsx";
    }

    public final class FILTER_CHAIN {
        public static final String AREA_IDS = "areaIds";
        public static final String BLOCK_IDS = "blockIds";
    }

}
