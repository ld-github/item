package com.ld.web.util;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * 
 *<p>Title: XmlHelper</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-11-21
 */
public class XmlHelper {

    private static Logger logger = Logger.getLogger(XmlHelper.class);

    private XmlMapper mapper;

    public static XmlHelper getInstance() {
        return new XmlHelper();
    }

    public String toXml(Object object) {
        try {
            return mapper.writeValueAsString(object).replace(" xmlns=\"\"", "");
        } catch (IOException e) {
            logger.error(String.format("Write to xml string error: %s", e.getMessage(), e));
            return null;
        }
    }

    public <T> T toObject(String xml, Class<T> clazz) {
        try {
            return StringUtil.isEmpty(xml) ? null : mapper.readValue(xml, clazz);
        } catch (IOException e) {
            logger.error(String.format("Parse xml string to object error: %s", xml, e));
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T toObject(String xml, TypeReference<?> typeRefer) {
        try {
            return StringUtil.isEmpty(xml) ? null : (T) mapper.readValue(xml, typeRefer);
        } catch (IOException e) {
            logger.error(String.format("Parse xml string to object error: %s", xml, e));
            return null;
        }
    }

    public XmlHelper() {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);

        mapper = new XmlMapper(module);
        mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(Feature.ALLOW_MISSING_VALUES, true);
    }

}
