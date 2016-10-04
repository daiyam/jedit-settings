/*
 * MetalColorTheme.java - MetalColor plugin
 * Copyright (C) 2008 Jocelyn Turcotte
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * $Id$
 */
 
package metalcolor;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import javax.swing.plaf.ColorUIResource; 
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.UIDefaults;

/** Overrides OceanTheme taking 3 base colors in input and keeping the same color ratio. */
public class MetalColorTheme extends OceanTheme
{
    public static final Color DEFAULT_CONTROLCOLOR = new Color(0xA3B8CC);
    public static final Color DEFAULT_TEXTCOLOR = new Color(0x333333);
    public static final Color DEFAULT_BGCOLOR = new Color(0xEEEEEE);
    public static final int DEFAULT_SCROLLBARWIDTH = 17;
    
    private final ColorUIResource primaryColor1;
    private final ColorUIResource primaryColor2;
    private final ColorUIResource primaryColor3;
    private final ColorUIResource secondaryColor1;
    private final ColorUIResource secondaryColor2;
    private final ColorUIResource secondaryColor3;

    private final ColorUIResource borderColor;
    private final ColorUIResource buttonGradientColor1;
    private final ColorUIResource buttonGradientColor2;
    private final ColorUIResource sliderGradientColor1;
    private final ColorUIResource sliderGradientColor2;
    private final ColorUIResource menuGradientColor1;
    private final ColorUIResource menuGradientColor2;
    
    private final ColorUIResource auxColor1;
    private final ColorUIResource auxColor2;
    private final ColorUIResource auxColor3;
    
    private final ColorUIResource blackColor;
    private final ColorUIResource whiteColor;
    
    private final ColorUIResource textColor;
    
    private final List buttonGradient;
    private final List sliderGradient;
    private final List menuGradient;
    private List scrollBarGradient;
    private int scrollBarWidth;

    public MetalColorTheme( Color controlColor, Color textColor, Color bgColor )
    {
        primaryColor1   = getRelativeColor( controlColor, 0.28f, -0.05f );
        primaryColor2   = new ColorUIResource( controlColor );
        primaryColor3   = getRelativeColor( controlColor, 0.0f, 0.10f );
        secondaryColor1 = getRelativeColor( controlColor, 0.0f, -0.20f );
        secondaryColor2 = getRelativeColor( controlColor, 0.0f, 0.10f );
        secondaryColor3 = new ColorUIResource( bgColor );

        borderColor         = getRelativeColor( controlColor, -0.60f, 0.0f );
        buttonGradientColor1= getRelativeColor( controlColor, -0.11f, 0.15f );
        buttonGradientColor2= getRelativeColor( controlColor, -0.80f, 0.20f );
        sliderGradientColor1= getRelativeColor( controlColor, -0.03f, 0.15f );
        sliderGradientColor2= getRelativeColor( controlColor, -0.80f, 0.20f );
        menuGradientColor1  = getRelativeColor( bgColor, 0.0f, 0.07f );
        menuGradientColor2  = getRelativeColor( bgColor, 0.0f, -0.08f );
        auxColor1 = getRelativeColor( controlColor, -0.5f, 0.05f );
        auxColor2 = getRelativeColor( controlColor, -0.08f, 0.14f );
        auxColor3 = getRelativeColor( controlColor, -0.02f, 0.20f );
        
        buttonGradient = Arrays.asList( new Object[] {
            new Float(.3f), new Float(0f),
            buttonGradientColor1, buttonGradientColor2, secondaryColor2 } );

        sliderGradient = Arrays.asList( new Object[] {
            new Float(.3f), new Float(.2f),
            sliderGradientColor1, sliderGradientColor2, secondaryColor2 } );
        
        menuGradient = Arrays.asList( new Object[] {
            new Float(1f), new Float(0f),
            menuGradientColor1, menuGradientColor2, menuGradientColor2 } );        

        scrollBarGradient = buttonGradient;
        scrollBarWidth = DEFAULT_SCROLLBARWIDTH;

        this.textColor = new ColorUIResource( textColor );
        
        // Override black color since it is used directly in some places (like tooltips)
        blackColor = new ColorUIResource( textColor );
        whiteColor = getRelativeColor( bgColor, 0.0f, 0.25f );
    }
    
    public void setScrollBarColor(Color controlColor)
    {
        ColorUIResource sbGradientColor1= getRelativeColor( controlColor, -0.11f, 0.15f );
        ColorUIResource sbGradientColor2= getRelativeColor( controlColor, -0.80f, 0.20f );
        ColorUIResource sbSecondaryColor2 = getRelativeColor( controlColor, 0.0f, 0.10f );

        scrollBarGradient = Arrays.asList( new Object[] {
            new Float(.3f), new Float(0f),
            sbGradientColor1, sbGradientColor2, sbSecondaryColor2 } );
    }
    
    public void setScrollBarWidth(int width)
    {
        scrollBarWidth = width;
    }

    public void addCustomEntriesToTable(UIDefaults table)
    {
        // Call OceanTheme's one then override our properties
        super.addCustomEntriesToTable( table );
        
        // The rest was filtered from OpenSDK's OceanTheme.java file.
        Object[] defaults = new Object[] {
            "Button.gradient", buttonGradient,
            "Button.toolBarBorderBackground", getInactiveControlTextColor(),
            "Button.disabledToolBarBorderBackground", borderColor,

            "CheckBox.gradient", buttonGradient,

            "CheckBoxMenuItem.gradient", buttonGradient,

            "MenuBar.gradient", menuGradient,
            "MenuBar.borderColor", borderColor,

            "InternalFrame.activeTitleGradient", buttonGradient,

            "RadioButton.gradient", buttonGradient,

            "RadioButtonMenuItem.gradient", buttonGradient,

            "ScrollBar.gradient", scrollBarGradient,
            "ScrollBar.width", scrollBarWidth,

            "Slider.altTrackColor", auxColor2,
            "Slider.gradient", sliderGradient,
            "Slider.focusGradient", sliderGradient,

            "SplitPane.dividerFocusColor", sliderGradientColor1,

            "TabbedPane.borderHightlightColor", getPrimary1(),
            "TabbedPane.contentAreaColor", sliderGradientColor1,
            "TabbedPane.selected", sliderGradientColor1,
            "TabbedPane.tabAreaBackground", auxColor1,
            "TabbedPane.unselectedBackground", getSecondary3(),

            "Table.gridColor", getSecondary1(),
            "TableHeader.focusCellBackground", sliderGradientColor1,

            "ToggleButton.gradient", buttonGradient,

            "ToolBar.borderColor", borderColor,

            "Tree.selectionBorderColor", getPrimary1(),
            "Tree.dropLineColor", getPrimary1(),
            "Table.dropLineColor", getPrimary1(),
            "Table.dropLineShortColor", getBlack(),

            "Table.dropCellBackground", auxColor3,
            "Tree.dropCellBackground", auxColor3,
            "List.dropCellBackground", auxColor3,
            "List.dropLineColor", getPrimary1()
        };
        table.putDefaults(defaults);
    }

    public String getName() { return "MetalColorTheme"; }

    public ColorUIResource getBlack() { return blackColor; }
    public ColorUIResource getWhite() { return whiteColor; }
    
    public ColorUIResource getControlTextColor() { return getBlack(); }
    public ColorUIResource getSystemTextColor() { return getBlack(); }
    public ColorUIResource getUserTextColor() { return getBlack(); }
    public ColorUIResource getWindowTitleForeground() { return getBlack(); }
    public ColorUIResource getWindowTitleInactiveForeground() { return super.getBlack(); }
    public ColorUIResource getMenuForeground() { return  getBlack(); }
    public ColorUIResource getMenuSelectedForeground() { return getBlack(); }
    public ColorUIResource getAcceleratorSelectedForeground() { return getBlack(); }
    
    protected ColorUIResource getPrimary1() { return primaryColor1; }
    protected ColorUIResource getPrimary2() { return primaryColor2; }
    protected ColorUIResource getPrimary3() { return primaryColor3; }
    protected ColorUIResource getSecondary1() { return secondaryColor1; }
    protected ColorUIResource getSecondary2() { return secondaryColor2; }
    protected ColorUIResource getSecondary3() { return secondaryColor3; }
    
    private ColorUIResource getRelativeColor( Color baseColor, float saturationDelta, float brightnessDelta )
    {
        float[] hsbVals = new float[3];
        Color.RGBtoHSB( baseColor.getRed()
                      , baseColor.getGreen()
                      , baseColor.getBlue()
                      , hsbVals );
        
        float sat = hsbVals[1] + saturationDelta;
        if( sat < 0.0f )        sat = 0.0f;
        else if( sat > 1.0f )   sat = 1.0f;
        
        float bri = hsbVals[2] + brightnessDelta;
        if( bri < 0.0f )        bri = 0.0f;
        else if( bri > 1.0f )   bri = 1.0f;
        
        int newColor = Color.HSBtoRGB( hsbVals[0] , sat , bri );
        
        return new ColorUIResource( newColor );
    }
}
