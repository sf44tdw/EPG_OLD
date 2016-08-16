/*
 * Copyright (C) 2016 normal
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package epgtools.libepgupdate.common.util;

import java.io.File;
import java.util.List;

/**
 *
 * @author normal
 */
public final class Util {

    private Util() {
    }

    public static boolean isSamePath(File f1, File f2) {
        return (f1.getAbsolutePath() == null ? f2.getAbsolutePath() == null : f1.getAbsolutePath().equals(f2.getAbsolutePath()));
    }

    public static boolean isSameFileList(List<File> l1, List<File> l2) {
        if (l1.size() != l2.size()) {
            return false;
        }
        boolean ret = true;
        for (File f : l1) {
            ret = ret && l2.contains(f);
        }
        for (File f : l2) {
            ret = ret && l1.contains(f);
        }
        return ret;
    }
}
