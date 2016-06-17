/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.type;

import java.util.*;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public enum Country {

    AF ("AF", "Afghanistan"),
    AX ("AX", "Aland Islands"),
    AL ("AL", "Albania"),
    DZ ("DZ", "Algeria"),
    AS ("AS", "American Samoa (US)"),
    AD ("AD", "Andorra"),
    AO ("AO", "Angola"),
    AI ("AI", "Anguilla (UK)"),
    AQ ("AQ", "Antarctica"),
    AG ("AG", "Antigua and Barbuda"),
    AR ("AR", "Argentina"),
    AM ("AM", "Armenia"),
    AW ("AW", "Aruba"),
    AU ("AU", "Australia"),
    AT ("AT", "Austria"),
    AZ ("AZ", "Azerbaijan"),
    BS ("BS", "Bahamas"),
    BH ("BH", "Bahrain"),
    BD ("BD", "Bangladesh"),
    BB ("BB", "Barbados"),
    BY ("BY", "Belarus"),
    BE ("BE", "Belgium"),
    BZ ("BZ", "Belize"),
    BJ ("BJ", "Benin"),
    BM ("BM", "Bermuda (UK)"),
    BT ("BT", "Bhutan"),
    BO ("BO", "Bolivia"),
    BA ("BA", "Bosnia and Herzegovina"),
    BW ("BW", "Botswana"),
    BV ("BV", "Bouvet Island"),
    BR ("BR", "Brazil"),
    IO ("IO", "British Indian Ocean Territory"),
    VG ("VG", "British Virgin Islands"),
    BN ("BN", "Brunei"),
    BG ("BG", "Bulgaria"),
    BF ("BF", "Burkina Faso"),
    BI ("BI", "Burundi"),
    KH ("KH", "Cambodia"),
    CM ("CM", "Cameroon"),
    CA ("CA", "Canada"),
    CV ("CV", "Cape Verde"),
    KY ("KY", "Cayman Islands (UK)"),
    CF ("CF", "Central African Republic"),
    TD ("TD", "Chad"),
    CL ("CL", "Chile"),
    CN ("CN", "China"),
    CX ("CX", "Christmas Island (AU)"),
    CC ("CC", "Cocos (Keeling) Islands (AU)"),
    CO ("CO", "Colombia"),
    KM ("KM", "Comoros"),
    CD ("CD", "Congo, Democratic Republic of the"),
    CG ("CG", "Congo, Republic of the"),
    CK ("CK", "Cook Islands (NZ)"),
    CR ("CR", "Costa Rica"),
    CI ("CI", "Cote d'Ivoire"),
    HR ("HR", "Croatia"),
    CU ("CU", "Cuba"),
    CY ("CY", "Cyprus"),
    CZ ("CZ", "Czech Republic"),
    DK ("DK", "Denmark"),
    DJ ("DJ", "Djibouti"),
    DM ("DM", "Dominica"),
    DR ("DR", "Dominican Republic"),
    EC ("EC", "Ecuador"),
    EG ("EG", "Egypt"),
    SV ("SV", "El Salvador"),
    GQ ("GQ", "Equatorial Guinea"),
    ER ("ER", "Eritrea"),
    EE ("EE", "Estonia"),
    ET ("ET", "Ethiopia"),
    FK ("FK", "Falkland Islands (UK)"),
    FO ("FO", "Faroe Islands (DK)"),
    FJ ("FJ", "Fiji"),
    FI ("FI", "Finland"),
    FR ("FR", "France"),
    GF ("GF", "French Guiana (FR)"),
    PF ("PF", "French Polynesia (FR)"),
    TF ("TF", "French Southern Territories"),
    GA ("GA", "Gabon"),
    GM ("GM", "Gambia"),
    GE ("GE", "Georgia"),
    DE ("DE", "Germany"),
    GH ("GH", "Ghana"),
    GI ("GI", "Gibraltar (UK)"),
    GR ("GR", "Greece"),
    GL ("GL", "Greenland (DK)"),
    GD ("GD", "Grenada"),
    GP ("GP", "Guadeloupe (FR)"),
    GU ("GU", "Guam (US)"),
    GT ("GT", "Guatemala"),
    GG ("GG", "Guernsey (UK)"),
    GN ("GN", "Guinea"),
    GW ("GW", "Guinea-Bissau"),
    GY ("GY", "Guyana"),
    HT ("HT", "Haiti"),
    HM ("HM", "Heard Island and McDonald Islands"),
    HN ("HN", "Honduras"),
    HK ("HK", "Hong Kong (CN)"),
    HU ("HU", "Hungary"),
    IS ("IS", "Iceland"),
    IN ("IN", "India"),
    ID ("ID", "Indonesia"),
    IR ("IR", "Iran"),
    IQ ("IQ", "Iraq"),
    IE ("IE", "Ireland"),
    IM ("IM", "Isle of Man (UK)"),
    IL ("IL", "Israel"),
    IT ("IT", "Italy"),
    JM ("JM", "Jamaica"),
    JP ("JP", "Japan"),
    JE ("JE", "Jersey (UK)"),
    JO ("JO", "Jordan"),
    KZ ("KZ", "Kazakstan"),
    KE ("KE", "Kenya"),
    KI ("KI", "Kiribati"),
    KV ("KV", "Kosovo"),
    KW ("KW", "Kuwait"),
    KG ("KG", "Kyrgyzstan"),
    LA ("LA", "Laos"),
    LV ("LV", "Latvia"),
    LB ("LB", "Lebanon"),
    LS ("LS", "Lesotho"),
    LR ("LR", "Liberia"),
    LY ("LY", "Libya"),
    LI ("LI", "Liechtenstein"),
    LT ("LT", "Lithuania"),
    LU ("LU", "Luxembourg"),
    MO ("MO", "Macau (CN)"),
    MK ("MK", "Macedonia"),
    MG ("MG", "Madagascar"),
    MW ("MW", "Malawi"),
    MY ("MY", "Malaysia"),
    MV ("MV", "Maldives"),
    ML ("ML", "Mali"),
    MT ("MT", "Malta"),
    MH ("MH", "Marshall islands"),
    MQ ("MQ", "Martinique (FR)"),
    MR ("MR", "Mauritania"),
    MU ("MU", "Mauritius"),
    YT ("YT", "Mayotte (FR)"),
    MX ("MX", "Mexico"),
    FM ("FM", "Micronesia, Federated States of"),
    MD ("MD", "Moldova"),
    MC ("MC", "Monaco"),
    MN ("MN", "Mongolia"),
    ME ("ME", "Montenegro"),
    MS ("MS", "Montserrat (UK)"),
    MA ("MA", "Morocco"),
    MZ ("MZ", "Mozambique"),
    MM ("MM", "Myanmar"),
    NA ("NA", "Namibia"),
    NR ("NR", "Nauru"),
    NP ("NP", "Nepal"),
    NL ("NL", "Netherlands"),
    AN ("AN", "Netherlands Antilles (NL)"),
    NC ("NC", "New Caledonia (FR)"),
    NZ ("NZ", "New Zealand"),
    NI ("NI", "Nicaragua"),
    NE ("NE", "Niger"),
    NG ("NG", "Nigeria"),
    NU ("NU", "Niue"),
    NF ("NF", "Norfolk Island (AU)"),
    KP ("KP", "North Korea"),
    MP ("MP", "Northern Mariana Islands (US)"),
    NO ("NO", "Norway"),
    OM ("OM", "Oman"),
    PK ("PK", "Pakistan"),
    PW ("PW", "Palau"),
    PS ("PS", "Palestinian Territories"),
    PA ("PA", "Panama"),
    PG ("PG", "Papua New Guinea"),
    PY ("PY", "Paraguay"),
    PE ("PE", "Peru"),
    PH ("PH", "Philippines"),
    PN ("PN", "Pitcairn Islands (UK)"),
    PL ("PL", "Poland"),
    PT ("PT", "Portugal"),
    PR ("PR", "Puerto Rico (US)"),
    QA ("QA", "Qatar"),
    RE ("RE", "Reunion (FR)"),
    RO ("RO", "Romania"),
    RU ("RU", "Russia"),
    RW ("RW", "Rwanda"),
    BL ("BL", "Saint Barthelemy"),
    SH ("SH", "Saint Helena (UK)"),
    KN ("KN", "Saint Kitts and Nevis"),
    LC ("LC", "Saint Lucia"),
    MF ("MF", "Saint Martin"),
    PM ("PM", "Saint Pierre and Miquelon (FR)"),
    VC ("VC", "Saint Vincent and the Grenadines"),
    WS ("WS", "Samoa"),
    SM ("SM", "San Marino"),
    ST ("ST", "Sao Tome and Principe"),
    SA ("SA", "Saudi Arabia"),
    SN ("SN", "Senegal"),
    RS ("RS", "Serbia"),
    SC ("SC", "Seychelles"),
    SL ("SL", "Sierra Leone"),
    SG ("SG", "Singapore"),
    SK ("SK", "Slovakia"),
    SI ("SI", "Slovenia"),
    SB ("SB", "Solomon Islands"),
    SO ("SO", "Somalia"),
    ZA ("ZA", "South Africa"),
    GS ("GS", "South Georgia & South Sandwich Islands (UK)"),
    KR ("KR", "South Korea"),
    ES ("ES", "Spain"),
    LK ("LK", "Sri Lanka"),
    SD ("SD", "Sudan"),
    SR ("SR", "Suriname"),
    SJ ("SJ", "Svalbard and Jan Mayen"),
    SZ ("SZ", "Swaziland"),
    SE ("SE", "Sweden"),
    CH ("CH", "Switzerland"),
    SY ("SY", "Syria"),
    TW ("TW", "Taiwan"),
    TJ ("TJ", "Tajikistan"),
    TZ ("TZ", "Tanzania"),
    TH ("TH", "Thailand"),
    TL ("TL", "Timor-Leste"),
    TG ("TG", "Togo"),
    TK ("TK", "Tokelau"),
    TO ("TO", "Tonga"),
    TT ("TT", "Trinidad and Tobago"),
    TN ("TN", "Tunisia"),
    TR ("TR", "Turkey"),
    TM ("TM", "Turkmenistan"),
    TC ("TC", "Turks and Caicos Islands (UK)"),
    TV ("TV", "Tuvalu"),
    UG ("UG", "Uganda"),
    UA ("UA", "Ukraine"),
    AE ("AE", "United Arab Emirates"),
    GB ("GB", "United Kingdom"),
    US ("USA", "United States"),
    UM ("UM", "United States Minor Outlying Islands"),
    UY ("UY", "Uruguay"),
    UZ ("UZ", "Uzbekistan"),
    VU ("VU", "Vanuatu"),
    VA ("VA", "Vatican City"),
    VE ("VE", "Venezuela"),
    VN ("VN", "Vietnam"),
    VI ("VI", "Virgin Islands, U.S."),
    WF ("WF", "Wallis and Futuna (FR)"),
    EH ("EH", "Western Sahara"),
    YE ("YE", "Yemen"),
    ZM ("ZM", "Zambia"),
    ZW ("ZW", "Zimbabwe"),
    DEFAULT ("USA", "United States");

    private String value;   // ISO Code
    private String text;

    /**
     * A mapping between the country iso and its corresponding Country to facilitate lookup by ISO code.
     */
    private static Map<String, Country> isoToCountryNameMapping;

    private Country(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public static Country getCountry(String value) {
        if (isoToCountryNameMapping == null) {
            initMapping();
        }
        return isoToCountryNameMapping.get(value);
    }

    private static void initMapping() {
        isoToCountryNameMapping = new HashMap<String, Country>();
        for (Country c : values()) {
            isoToCountryNameMapping.put(c.value, c);
        }
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public static boolean contains(String value) {
        for (Country c : Country.values()) {
            if (c.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /*
     * randomizer
     */
    private static final List<Country> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static String getRandom()  {
        return VALUES.get(RANDOM.nextInt(SIZE)).getText();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("{value='").append(value).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public static void main(String[] args) {
        Country c = Country.US;
        System.out.println(c);
        System.out.println("value=" + c.getValue());
        System.out.println("text=" + c.getText());
        System.out.println("toString=" + c.toString());
        System.out.println("contains(true)=" + Country.contains(Country.US.getValue()));
        System.out.println("contains(false)=" + Country.contains("browser"));

        System.out.println("\n((( randomizer )))");
        for (int i = 0; i < 3; i++)
            System.out.println(Country.getRandom());
    }

}
