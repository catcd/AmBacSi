package vn.ahaay.ambacsi.api.device;

import android.graphics.Bitmap;

import vn.ahaay.ambacsi.api.GlobalContext;
import vn.ahaay.ambacsi.api.ambacsi.Constant;
import vn.ahaay.ambacsi.api.device.constant.ImageFileName;
import vn.ahaay.ambacsi.ui.AppDrawerActivity;

/**
 * Created by Can on 24-Sep-16.
 */

public class DeviceUserData {
    public static void deleteUserAvatar(int id) {
        new ImageSaver(GlobalContext.getContext())
                .setFileName(ImageFileName.USER_DATA_AVATAR_FILE_NAME)
                .delete();
    }

    // del all
    public static void deleteUserAvatar() {
        new ImageSaver(GlobalContext.getContext())
                .setFileName(ImageFileName.USER_DATA_AVATAR_FILE_NAME)
                .delete();
    }

    public static Bitmap loadUserAvatar(int id) {
        return new ImageSaver(GlobalContext.getContext())
                .setFileName(ImageFileName.USER_DATA_AVATAR_FILE_NAME)
                .load();
    }

    public static Bitmap loadUserAvatar() {
        return new ImageSaver(GlobalContext.getContext())
                .setFileName(ImageFileName.USER_DATA_AVATAR_FILE_NAME)
                .load();
    }
}
