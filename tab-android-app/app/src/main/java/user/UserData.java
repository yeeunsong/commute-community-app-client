package user;

public class UserData {
    private static String userId;
    private static String userEmail;
    private static String userCompany;

    public static String getUserId() {
        return userId;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static String getUserCompany() {
        return userCompany;
    }

    public static void setUserId(String userId) {
        UserData.userId = userId;
    }

    public static void setUserEmail(String userEmail) {
        UserData.userEmail = userEmail;
    }

    public static void setUserCompany(String userCompany) {
        UserData.userCompany = userCompany;
    }
}
