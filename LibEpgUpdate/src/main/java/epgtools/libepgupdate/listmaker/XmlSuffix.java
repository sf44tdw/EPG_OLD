/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.libepgupdate.listmaker;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

/**
 *
 * @author normal
 */
public final class XmlSuffix {

    private XmlSuffix() {
    }

    public static final IOFileFilter SUFFIX_1 = FileFilterUtils.suffixFileFilter("xml");
    public static final IOFileFilter SUFFIX_2 = FileFilterUtils.suffixFileFilter("Xml");
    public static final IOFileFilter SUFFIX_3 = FileFilterUtils.suffixFileFilter("xMl");
    public static final IOFileFilter SUFFIX_4 = FileFilterUtils.suffixFileFilter("xmL");
    public static final IOFileFilter SUFFIX_5 = FileFilterUtils.suffixFileFilter("XMl");
    public static final IOFileFilter SUFFIX_6 = FileFilterUtils.suffixFileFilter("xML");
    public static final IOFileFilter SUFFIX_7 = FileFilterUtils.suffixFileFilter("XmL");
    public static final IOFileFilter SUFFIX_8 = FileFilterUtils.suffixFileFilter("XML");

    public static IOFileFilter getFilter() {
        return FileFilterUtils.or(SUFFIX_1, SUFFIX_2, SUFFIX_3, SUFFIX_4, SUFFIX_5, SUFFIX_6, SUFFIX_7, SUFFIX_8);
    }
}
