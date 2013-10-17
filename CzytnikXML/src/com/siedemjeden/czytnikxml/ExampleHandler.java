package com.siedemjeden.czytnikxml;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

 

public class ExampleHandler extends DefaultHandler{

        // ===========================================================
        // Fields
        // ===========================================================


        private boolean in_maszyna = false;
        private boolean in_bm1 = false;
        private boolean in_bm2 = false;
        private boolean in_5130 = false;
        private boolean in_70361 = false;
        private boolean in_70362 = false;
        private boolean in_cell = false;
        private boolean in_nity = false;
        private boolean in_bm1Przywo = false;
        private boolean in_bm2Przywo = false;
        private boolean in_5130Przywo = false;
        private boolean in_zbrojenie = false;
        private boolean in_innertag = false;
        private boolean in_outertag = false;
        private boolean in_mytag = false;

        private ParsedExampleDataSet myParsedExampleDataSet = new ParsedExampleDataSet();

        // ===========================================================
        // Getter & Setter
        // ===========================================================

        public ParsedExampleDataSet getParsedData() {
                return this.myParsedExampleDataSet;
        }

        // ===========================================================
        // Methods
        // ===========================================================

        @Override

        public void startDocument() throws SAXException {
                this.myParsedExampleDataSet = new ParsedExampleDataSet();
        }

        @Override

        public void endDocument() throws SAXException {
                // Nothing to do
        }


        

        /** Gets be called on opening tags like: 
         * <tag> 
         * Can provide attribute(s), when xml was like:
         * <tag attribute="attributeValue">*/

        @Override
        public void startElement(String namespaceURI, String localName,
                        String qName, Attributes atts) throws SAXException {
                if (localName.equals("maszyna")) {
                        this.in_maszyna = true;
                }else if (localName.equals("bm1")) {
                        this.in_bm1 = true;
                }else if (localName.equals("bm2")) {
                    this.in_bm2 = true;
                }else if (localName.equals("tb5130")) {
                    this.in_5130 = true;
                }else if (localName.equals("tb70361")) {
                    this.in_70361 = true;
                }else if (localName.equals("tb70362")) {
                	this.in_70362 = true;
                }else if (localName.equals("cell")) {
                	this.in_cell = true;
                }else if (localName.equals("nity")) {
                	this.in_nity = true;
                }else if (localName.equals("zbrojenie")) {
                        this.in_zbrojenie = true;
                }else if (localName.equals("przywolanie")) {
                        // Extract an Attribute
                        String attrValue = atts.getValue("powod");
                        int i = Integer.parseInt(attrValue);
                        if(this.in_bm1){myParsedExampleDataSet.setExtractedIntBM1(i);}
                        if(this.in_bm2){myParsedExampleDataSet.setExtractedIntBM2(i);}
                        if(this.in_5130){myParsedExampleDataSet.setExtractedInt5130(i);}
                        if(this.in_70361){myParsedExampleDataSet.setExtractedInt70361(i);}
                        if(this.in_70362){myParsedExampleDataSet.setExtractedInt70362(i);}
                        if(this.in_cell){myParsedExampleDataSet.setExtractedIntCell(i);}
                        if(this.in_nity){myParsedExampleDataSet.setExtractedIntNity(i);}
                }
        }

        @Override
        public void endElement(String namespaceURI, String localName, String qName)
                        throws SAXException {
                if (localName.equals("maszyna")) {
                        this.in_maszyna = false;
                }else if (localName.equals("bm1")) {
                        this.in_bm1 = false;
                }else if (localName.equals("bm2")) {
                    this.in_bm2 = false;
                }else if (localName.equals("tb5130")) {
                    this.in_5130 = false;
                }else if (localName.equals("tb70361")) {
                    this.in_70361 = false;
                }else if (localName.equals("tb70362")) {
                	this.in_70362 = false;
                }else if (localName.equals("cell")) {
                	this.in_cell = false;
                }else if (localName.equals("nity")) {
                	this.in_nity = false;
                }else if (localName.equals("zbrojenie")) {
                        this.in_zbrojenie = false;
                }else if (localName.equals("powod")) {
                        // Nothing to do here
                }
        }


        /** Gets be called on the following structure: 
         * <tag>characters</tag> */
        @Override

    public void characters(char ch[], int start, int length) {
                if(this.in_zbrojenie){
                if(this.in_bm1&&myParsedExampleDataSet.getExtractedIntBM1()!=0){myParsedExampleDataSet.setExtractedStringBM1(new String(ch, start, length));}
                if(this.in_bm2&&myParsedExampleDataSet.getExtractedIntBM2()!=0){myParsedExampleDataSet.setExtractedStringBM2(new String(ch, start, length));}
                if(this.in_5130&&myParsedExampleDataSet.getExtractedInt5130()!=0){myParsedExampleDataSet.setExtractedString5130(new String(ch, start, length));}
                if(this.in_70361&&myParsedExampleDataSet.getExtractedInt70361()!=0){myParsedExampleDataSet.setExtractedString70361(new String(ch, start, length));}
                if(this.in_70362&&myParsedExampleDataSet.getExtractedInt70362()!=0){myParsedExampleDataSet.setExtractedString70362(new String(ch, start, length));}
                if(this.in_cell&&myParsedExampleDataSet.getExtractedIntCell()!=0){myParsedExampleDataSet.setExtractedStringCell(new String(ch, start, length));}
                if(this.in_nity&&myParsedExampleDataSet.getExtractedIntNity()!=0){myParsedExampleDataSet.setExtractedStringNity(new String(ch, start, length));}
         //       if(this.in_bm1&&myParsedExampleDataSet.getExtractedIntBM1()==0){myParsedExampleDataSet.setExtractedStringBM1("");}
         //       if(this.in_bm2&&myParsedExampleDataSet.getExtractedIntBM2()==0){myParsedExampleDataSet.setExtractedStringBM2("");}
         //       if(this.in_5130&&myParsedExampleDataSet.getExtractedInt5130()==0){myParsedExampleDataSet.setExtractedString5130("");}
        }
    }	
}