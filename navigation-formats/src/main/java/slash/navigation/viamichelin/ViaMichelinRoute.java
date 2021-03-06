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

package slash.navigation.viamichelin;

import slash.common.type.CompactCalendar;
import slash.navigation.base.SimpleRoute;
import slash.navigation.base.Wgs84Position;

import java.util.List;

import static slash.navigation.base.RouteCharacteristics.Route;

/**
 * A ViaMichelin (.xvm) route.
 *
 * @author Christian Pesch
 */

public class ViaMichelinRoute extends SimpleRoute<Wgs84Position, ViaMichelinFormat> {
    public ViaMichelinRoute(String name, List<Wgs84Position> positions) {
        super(new ViaMichelinFormat(), Route, name, positions);
    }

    public Wgs84Position createPosition(Double longitude, Double latitude, Double elevation, Double speed, CompactCalendar time, String description) {
        return new Wgs84Position(longitude, latitude, elevation, speed, time, description);
    }
}