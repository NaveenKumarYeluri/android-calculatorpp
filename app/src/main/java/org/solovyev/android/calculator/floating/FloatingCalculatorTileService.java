package org.solovyev.android.calculator.floating;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import androidx.annotation.RequiresApi;

import org.solovyev.android.calculator.R;

@RequiresApi(api = Build.VERSION_CODES.N)
public class FloatingCalculatorTileService extends TileService {

    @Override
    public void onStartListening() {
        super.onStartListening();
        updateTileState();
    }

    @Override
    public void onClick() {
        super.onClick();
        final boolean hasPermission = FloatingCalculatorView.isOverlayPermissionGranted(this);
        if (!hasPermission) {
            final Intent intent = new Intent(this, FloatingCalculatorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 34) {
                final PendingIntent pi = PendingIntent.getActivity(this, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                startActivityAndCollapse(pi);
            } else {
                startActivityAndCollapse(intent);
            }
        } else {
            if (FloatingCalculatorService.isShowing()) {
                FloatingCalculatorService.hide(this);
            } else {
                FloatingCalculatorService.show(this);
            }
        }
        updateTileState();
    }

    private void updateTileState() {
        final Tile tile = getQsTile();
        if (tile != null) {
            final boolean isShowing = FloatingCalculatorService.isShowing();
            tile.setState(isShowing ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                tile.setSubtitle(getString(isShowing ? R.string.cpp_tile_active : R.string.cpp_tile_inactive));
            }
            tile.updateTile();
        }
    }
}
