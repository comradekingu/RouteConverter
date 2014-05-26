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

package slash.navigation.converter.gui.helpers;

import slash.common.type.CompactCalendar;
import slash.navigation.converter.gui.RouteConverter;
import slash.navigation.converter.gui.mapview.MapView;
import slash.navigation.converter.gui.mapview.MapViewCallback;
import slash.navigation.converter.gui.models.PositionsModel;
import slash.navigation.routing.RoutingService;
import slash.navigation.routing.TravelMode;

import javax.swing.event.ChangeListener;
import java.util.Calendar;

import static slash.common.type.CompactCalendar.UTC;
import static slash.common.type.CompactCalendar.fromCalendar;
import static slash.navigation.base.RouteCalculations.extrapolateTime;
import static slash.navigation.converter.gui.models.PositionColumns.DATE_TIME_COLUMN_INDEX;

/**
 * Implements the callbacks from the {@link MapView} to the other RouteConverter services.
 *
 * @author Christian Pesch
 */

public class MapViewCallbackImpl implements MapViewCallback {
    public String createDescription(int index, String description) {
        return RouteConverter.getInstance().getBatchPositionAugmenter().createDescription(index, description);
    }

    public void complementElevation(int[] rows) {
        RouteConverter.getInstance().getBatchPositionAugmenter().addElevations(RouteConverter.getInstance().getPositionsView(), RouteConverter.getInstance().getPositionsModel(), rows);
    }

    public void complementDescription(int row) {
        RouteConverter.getInstance().getBatchPositionAugmenter().addDescriptions(RouteConverter.getInstance().getPositionsView(), RouteConverter.getInstance().getPositionsModel(), new int[]{row});
    }

    public void complementTime(int row, boolean allowCurrentTime) { // TODO check with BatchPositionAugmenter
        PositionsModel positionsModel = RouteConverter.getInstance().getPositionsModel();
        // do not put this in executorService since when called in batches, the edit() must happen before the
        // next time can be complemented
        CompactCalendar interpolated = row - 2 >= 0 ? extrapolateTime(positionsModel.getPosition(row),
                positionsModel.getPosition(row - 1), positionsModel.getPosition(row - 2)) : null;
        // since interpolation is just between the previous positions this leads to errors when inserting
        // more than one position for which no time can be interpolated from the previous positions
        if (interpolated == null && allowCurrentTime)
            interpolated = fromCalendar(Calendar.getInstance(UTC));
        positionsModel.edit(row, DATE_TIME_COLUMN_INDEX, interpolated, -1, null, true, false);
    }

    public RoutingService getRoutingService() {
        return RouteConverter.getInstance().getRoutingServiceFacade().getRoutingService();
    }

    public TravelMode getTravelMode() {
        return RouteConverter.getInstance().getRoutingServiceFacade().getTravelMode();
    }

    public boolean isAvoidHighways() {
        return RouteConverter.getInstance().getRoutingServiceFacade().isAvoidHighways();
    }

    public boolean isAvoidTolls() {
        return RouteConverter.getInstance().getRoutingServiceFacade().isAvoidTolls();
    }

    public void addChangeListener(ChangeListener l) {
        RouteConverter.getInstance().getRoutingServiceFacade().addChangeListener(l);
    }
}
