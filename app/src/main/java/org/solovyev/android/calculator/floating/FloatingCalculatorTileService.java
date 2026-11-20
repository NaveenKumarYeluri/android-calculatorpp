package org.solovyev.android.calculator.floating;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class FloatingCalculatorTileService extends TileService {

    @Override
    public void onStartListening() {
        super.onStartListening();
        final Tile tile = getQsTile();
        if (tile != null) {
            tile.setState(Tile.STATE_INACTIVE);
            tile.updateTile();
        }
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
            FloatingCalculatorService.show(this);
        }
    }
}
