package com.siedemjeden.czytnikxml;

public class ParsedExampleDataSet {

    private String extractedStringBM1 = null;
    private String extractedStringBM2 = null;
    private String extractedString5130 = null;
    private String extractedString70361 = null;
    private String extractedString70362 = null;
    private String extractedStringCell = null;
    private String extractedStringNity = null;
    private int extractedIntBM1 = 0;
    private int extractedIntBM2 = 0;
    private int extractedInt5130 = 0;
    private int extractedInt70361 = 0;
    private int extractedInt70362 = 0;
    private int extractedIntCell = 0;
    private int extractedIntNity = 0;

    public String getExtractedBM1() {
        return extractedStringBM1;
    }
    public String getExtractedBM2() {
        return extractedStringBM2;
    }
    public String getExtracted5130() {
        return extractedString5130;
    }
    public String getExtracted70361() {
        return extractedString70361;
    }
    public String getExtracted70362() {
        return extractedString70362;
    }
    public String getExtractedCell() {
        return extractedStringCell;
    }
    public String getExtractedNity() {
        return extractedStringNity;
    }
    
    public void setExtractedStringBM1(String extractedString) {
        this.extractedStringBM1 = extractedString;
    }
    public void setExtractedStringBM2(String extractedString) {
        this.extractedStringBM2 = extractedString;
    }
    public void setExtractedString5130(String extractedString) {
        this.extractedString5130 = extractedString;
    }
    public void setExtractedString70361(String extractedString) {
        this.extractedString70361 = extractedString;
    }
    public void setExtractedString70362(String extractedString) {
        this.extractedString70362 = extractedString;
    }
    public void setExtractedStringCell(String extractedString) {
        this.extractedStringCell = extractedString;
    }
    public void setExtractedStringNity(String extractedString) {
        this.extractedStringNity = extractedString;
    }

    public int getExtractedIntBM1() {
        return extractedIntBM1;
    }
    public int getExtractedIntBM2() {
        return extractedIntBM2;
    }
    public int getExtractedInt5130() {
        return extractedInt5130;
    }
    public int getExtractedInt70361() {
        return extractedInt70361;
    }
    public int getExtractedInt70362() {
        return extractedInt70362;
    }
    public int getExtractedIntCell() {
        return extractedIntCell;
    }
    public int getExtractedIntNity() {
        return extractedIntNity;
    }

    public void setExtractedIntBM1(int extractedInt) {
        this.extractedIntBM1 = extractedInt;
    }
    public void setExtractedIntBM2(int extractedInt) {
        this.extractedIntBM2 = extractedInt;
    }
    public void setExtractedInt5130(int extractedInt) {
        this.extractedInt5130 = extractedInt;
    }
    public void setExtractedInt70361(int extractedInt) {
        this.extractedInt70361 = extractedInt;
    }
    public void setExtractedInt70362(int extractedInt) {
        this.extractedInt70362 = extractedInt;
    }
    public void setExtractedIntCell(int extractedInt) {
        this.extractedIntCell = extractedInt;
    }
    public void setExtractedIntNity(int extractedInt) {
        this.extractedIntNity = extractedInt;
    }


    public String toStringBM1(){
        return "Zbrojenie= " + this.extractedStringBM1;
                       //     + "/nExtractedInt = " + this.extractedInt;
    }
    public String toStringBM2(){
        return "Zbrojenie= " + this.extractedStringBM2;
    }
    public String toString5130(){
    	return "Zbrojenie= " + this.extractedString5130;
    }
    public String toString70361(){
        return "Zbrojenie= " + this.extractedString70361;
    }
    public String toString70362(){
    	return "Zbrojenie= " + this.extractedString70362;
    }
    public String toStringCell(){
        return "Zbrojenie= " + this.extractedStringCell;
    }
    public String toStringNity(){
    	return "Zbrojenie= " + this.extractedStringNity;
    }
}