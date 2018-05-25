package com.accAyo.serverProjectDemo.util.code;

import com.jhlabs.image.PinchFilter;
import com.jhlabs.math.ImageFunction2D;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByBufferedImageOp;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.GlyphsPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.*;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:32 2018/5/24
 */
public class CaptchaEngine extends ListImageCaptchaEngine {
    @Override
    protected void buildInitialFactories() {
        com.octo.captcha.component.word.wordgenerator.WordGenerator wgen = new RandomWordGenerator("abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789");
        TextPaster randomPaster = new GlyphsPaster(4, 4,
                new SingleColorGenerator(new Color(23, 67, 172)),
//	                new RandomListColorGenerator(
//	                        new Color[]{
//	                                new Color(23, 170, 27),	//绿
//	                                new Color(220, 34, 11), //红
//	                                new Color(23, 67, 172)}),
                new GlyphsVisitors[]{
                        new TranslateGlyphsVerticalRandomVisitor(1),
                        new RotateGlyphsRandomVisitor(Math.PI/8), //旋转
                        new OverlapGlyphsUsingShapeVisitor(2), //重叠区域
                        new TranslateAllToRandomPointVisitor()
                });

        BackgroundGenerator back = new UniColorBackgroundGenerator(
                91, 37, Color.white);

        FontGenerator shearedFont = new RandomFontGenerator(28,
                30,
                new Font[]{
                        new Font("nyala",Font.PLAIN, 30)
                        ,
                        new Font("Bell MT",  Font.PLAIN, 30)
//	                        ,
//	                        new Font("Credit valley",  Font.PLAIN, 30)
                }
                ,true);


        PinchFilter pinch = new PinchFilter();

        pinch.setAmount(-.5f);
        pinch.setRadius(30);
        pinch.setAngle((float) (Math.PI/16));
        pinch.setCentreX(0.5f);
        pinch.setCentreY(-0.01f);
        pinch.setEdgeAction(ImageFunction2D.CLAMP);

        PinchFilter pinch2 = new PinchFilter();
        pinch2.setAmount(-.6f);
        pinch2.setRadius(30);//半径
        pinch2.setAngle((float) (Math.PI/16));//角度
        pinch2.setCentreX(0.3f);
        pinch2.setCentreY(1.01f);
        pinch2.setEdgeAction(ImageFunction2D.CLAMP);

        PinchFilter pinch3 = new PinchFilter();
        pinch3.setAmount(-.6f);
        pinch3.setRadius(30);
        pinch3.setAngle((float) (Math.PI/16));
        pinch3.setCentreX(0.8f);
        pinch3.setCentreY(-0.01f);
        pinch3.setEdgeAction(ImageFunction2D.CLAMP);



        List<ImageDeformation> textDef =  new ArrayList<ImageDeformation>();
        textDef.add(new ImageDeformationByBufferedImageOp(pinch));
        textDef.add(new ImageDeformationByBufferedImageOp(pinch2));
        textDef.add(new ImageDeformationByBufferedImageOp(pinch3));

        //word2image 1
        com.octo.captcha.component.image.wordtoimage.WordToImage word2image;
        word2image = new DeformedComposedWordToImage(false,shearedFont, back, randomPaster,
                new ArrayList<ImageDeformation>(),
                new ArrayList<ImageDeformation>(),
                textDef
        );


        this.addFactory(
                new com.octo.captcha.image.gimpy.GimpyFactory(wgen,
                        word2image, false));
    }
}
