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
package slash.navigation.itn;

import slash.common.type.CompactCalendar;
import slash.navigation.base.BaseRoute;
import slash.navigation.base.RouteCharacteristics;
import slash.navigation.base.SimpleFormat;
import slash.navigation.base.SimpleRoute;
import slash.navigation.base.Wgs84Position;
import slash.navigation.base.Wgs84Route;
import slash.navigation.bcr.BcrFormat;
import slash.navigation.bcr.BcrPosition;
import slash.navigation.bcr.BcrRoute;
import slash.navigation.fpl.GarminFlightPlanPosition;
import slash.navigation.fpl.GarminFlightPlanRoute;
import slash.navigation.gopal.GoPal3Route;
import slash.navigation.gopal.GoPal5Route;
import slash.navigation.gopal.GoPalPosition;
import slash.navigation.gpx.Gpx10Format;
import slash.navigation.gpx.Gpx11Format;
import slash.navigation.gpx.GpxFormat;
import slash.navigation.gpx.GpxPosition;
import slash.navigation.gpx.GpxRoute;
import slash.navigation.kml.BaseKmlFormat;
import slash.navigation.kml.KmlPosition;
import slash.navigation.kml.KmlRoute;
import slash.navigation.lmx.NokiaLandmarkExchangeFormat;
import slash.navigation.nmea.BaseNmeaFormat;
import slash.navigation.nmea.NmeaPosition;
import slash.navigation.nmea.NmeaRoute;
import slash.navigation.nmn.NmnFormat;
import slash.navigation.nmn.NmnPosition;
import slash.navigation.nmn.NmnRoute;
import slash.navigation.tcx.Tcx1Format;
import slash.navigation.tcx.Tcx2Format;

import java.util.ArrayList;
import java.util.List;

import static slash.navigation.util.RouteComments.createRouteName;

/**
 * Represents the route in a Tom Tom Route (.itn) file.
 *
 * @author Christian Pesch
 */

public class TomTomRoute extends BaseRoute<TomTomPosition, TomTomRouteFormat> {
    private String name;
    private final List<TomTomPosition> positions;

    public TomTomRoute(TomTomRouteFormat format, RouteCharacteristics characteristics, String name, List<TomTomPosition> positions) {
        super(format, characteristics);
        this.name = name;
        this.positions = positions;
    }

    public TomTomRoute(RouteCharacteristics characteristics, String name, List<TomTomPosition> positions) {
        this(new TomTom5RouteFormat(), characteristics, name, positions);
    }

    public String getName() {
        return name != null ? name : createRouteName(positions);
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDescription() {
        return null;
    }

    public List<TomTomPosition> getPositions() {
        return positions;
    }

    public int getPositionCount() {
        return positions.size();
    }

    public void add(int index, TomTomPosition position) {
        positions.add(index, position);
    }

    public TomTomPosition createPosition(Double longitude, Double latitude, Double elevation, Double speed, CompactCalendar time, String comment) {
        return new TomTomPosition(longitude, latitude, elevation, speed, time, comment);
    }

    protected BcrRoute asBcrFormat(BcrFormat format) {
        List<BcrPosition> bcrPositions = new ArrayList<BcrPosition>();
        for (TomTomPosition tomTomPosition : positions) {
            BcrPosition bcrPosition = tomTomPosition.asMTPPosition();
            // shortens comment to better fit to Map&Guide Tourenplaner station list
            String location = tomTomPosition.getCity();
            if (location != null)
                bcrPosition.setComment(location);
            bcrPositions.add(bcrPosition);
        }
        return new BcrRoute(format, getName(), getDescription(), bcrPositions);
    }

    protected KmlRoute asKmlFormat(BaseKmlFormat format) {
        List<KmlPosition> kmlPositions = new ArrayList<KmlPosition>();
        for (TomTomPosition tomTomPosition : positions) {
            kmlPositions.add(tomTomPosition.asKmlPosition());
        }
        return new KmlRoute(format, getCharacteristics(), getName(), getDescription(), kmlPositions);
    }

    protected NmeaRoute asNmeaFormat(BaseNmeaFormat format) {
        List<NmeaPosition> nmeaPositions = new ArrayList<NmeaPosition>();
        for (TomTomPosition position : positions) {
            nmeaPositions.add(position.asNmeaPosition());
        }
        return new NmeaRoute(format, getCharacteristics(), nmeaPositions);
    }

    protected NmnRoute asNmnFormat(NmnFormat format) {
        List<NmnPosition> nmnPositions = new ArrayList<NmnPosition>();
        for (TomTomPosition tomTomPosition : positions) {
            nmnPositions.add(tomTomPosition.asNmnPosition());
        }
        return new NmnRoute(format, getCharacteristics(), name, nmnPositions);
    }

    protected SimpleRoute asSimpleFormat(SimpleFormat format) {
        List<Wgs84Position> simplePositions = new ArrayList<Wgs84Position>();
        for (TomTomPosition tomTomPosition : positions) {
            simplePositions.add(tomTomPosition.asWgs84Position());
        }
        return new Wgs84Route(format, getCharacteristics(), simplePositions);
    }

    protected TomTomRoute asTomTomRouteFormat(TomTomRouteFormat format) {
        List<TomTomPosition> tomTomPositions = new ArrayList<TomTomPosition>();
        for (TomTomPosition position : positions) {
            tomTomPositions.add(position.asTomTomRoutePosition());
        }
        return new TomTomRoute(format, getCharacteristics(), getName(), tomTomPositions);
    }

    public GarminFlightPlanRoute asGarminFlightPlanFormat() {
        List<GarminFlightPlanPosition> flightPlanPositions = new ArrayList<GarminFlightPlanPosition>();
        for (TomTomPosition position : positions) {
            flightPlanPositions.add(position.asGarminFlightPlanPosition());
        }
        return new GarminFlightPlanRoute(getName(), getDescription(), flightPlanPositions);
    }

    private GpxRoute asGpxFormat(GpxFormat format) {
        List<GpxPosition> gpxPositions = new ArrayList<GpxPosition>();
        for (TomTomPosition tomTomPosition : positions) {
            gpxPositions.add(tomTomPosition.asGpxPosition());
        }
        return new GpxRoute(format, getCharacteristics(), getName(), getDescription(), gpxPositions);
    }

    public GpxRoute asGpx10Format() {
        return asGpxFormat(new Gpx10Format());
    }

    public GpxRoute asGpx11Format() {
        return asGpxFormat(new Gpx11Format());
    }

    public GpxRoute asTcx1Format() {
        return asGpxFormat(new Tcx1Format());
    }

    public GpxRoute asTcx2Format() {
        return asGpxFormat(new Tcx2Format());
    }

    public GpxRoute asNokiaLandmarkExchangeFormat() {
        return asGpxFormat(new NokiaLandmarkExchangeFormat());
    }

    public GoPal3Route asGoPal3RouteFormat() {
        List<GoPalPosition> gopalPositions = new ArrayList<GoPalPosition>();
        for (TomTomPosition position : positions) {
            gopalPositions.add(position.asGoPalRoutePosition());
        }
        return new GoPal3Route(getName(), gopalPositions);
    }

    public GoPal5Route asGoPal5RouteFormat() {
        List<GoPalPosition> gopalPositions = new ArrayList<GoPalPosition>();
        for (TomTomPosition position : positions) {
            gopalPositions.add(position.asGoPalRoutePosition());
        }
        return new GoPal5Route(getName(), gopalPositions);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TomTomRoute route = (TomTomRoute) o;

        return !(name != null ? !name.equals(route.name) : route.name != null) &&
                characteristics.equals(route.characteristics) &&
                positions.equals(route.positions);
    }

    public int hashCode() {
        int result;
        result = (name != null ? name.hashCode() : 0);
        result = 29 * result + characteristics.hashCode();
        result = 29 * result + positions.hashCode();
        return result;
    }
}
