/*
    This file is part of RouteConverter.

    RouteConverter is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    RouteConverter is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with RouteConverter; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

    Copyright (C) 2007 Christian Pesch. All Rights Reserved.
*/
package slash.navigation.converter.gui;

import slash.navigation.converter.gui.helpers.MapViewCallbackImpl;
import slash.navigation.gui.models.BooleanModel;
import slash.navigation.elevation.ElevationService;
import slash.navigation.gui.Application;
import slash.navigation.gui.notifications.NotificationManager;
import slash.navigation.maps.mapsforge.MapsforgeMapManager;
import slash.navigation.mapview.MapView;
import slash.navigation.mapview.mapsforge.MapViewCallbackOffline;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import static java.text.MessageFormat.format;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import static slash.common.helpers.ExceptionHelper.getLocalizedMessage;
import static slash.navigation.gui.helpers.WindowHelper.getFrame;
import static slash.navigation.gui.helpers.WindowHelper.handleOutOfMemoryError;

/**
 * Implements the callbacks from the {@link MapView} to the other RouteConverter services including the {@link MapsforgeMapManager}
 * for the RouteConverter Offline Edition.
 *
 * @author Christian Pesch
 */

public class MapViewCallbackOfflineImpl extends MapViewCallbackImpl implements MapViewCallbackOffline {
    private static final Logger log = Logger.getLogger(MapViewCallbackOfflineImpl.class.getName());

    public MapsforgeMapManager getMapsforgeMapManager() {
        return ((RouteConverterOffline) Application.getInstance()).getMapsforgeMapManager();
    }

    public BooleanModel getShowShadedHills() {
        return ((RouteConverterOffline) Application.getInstance()).getShowShadedHills();
    }

    public ElevationService getElevationService() {
        return ((RouteConverter) Application.getInstance()).getElevationServiceFacade().getElevationService();
    }

    private NotificationManager getNotificationManager() {
        return Application.getInstance().getContext().getNotificationManager();
    }

    private ResourceBundle getBundle() {
        return Application.getInstance().getContext().getBundle();
    }

    public void showDownloadNotification() {
        getNotificationManager().showNotification(getBundle().getString("downloading-routing-data"), null);
    }

    public void showProcessNotification() {
        getNotificationManager().showNotification(getBundle().getString("processing-routing-data"), null);
    }

    public void handleRoutingException(Throwable t) {
        if (t instanceof OutOfMemoryError)
            handleOutOfMemoryError(OutOfMemoryError.class.cast(t));
        else {
            log.severe("Cannot route position list: " + getLocalizedMessage(t));
            showMessageDialog(getFrame(), format(getBundle().getString("cannot-route-position-list"), t),
                    getFrame().getTitle(), ERROR_MESSAGE);
        }
    }

    public void showMapException(String mapName, Exception e) {
        log.severe("Cannot display map " + mapName + ": " + getLocalizedMessage(e));
        showMessageDialog(getFrame(), format(getBundle().getString("cannot-display-map"), mapName, e),
                getFrame().getTitle(), ERROR_MESSAGE);
    }
}
