package com.hnmobile.wechat.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class CardDrawer {

	static Logger log = Logger.getLogger(CardDrawer.class);
	/**
	 * 画卡的正面 
	 * @throws IOException 
	 */
	public static void drawSide1( String templateImgPath,String merchantName,String cardNumber,String newImgPath ) throws IOException{
		BufferedImage buffImage =  ImageIO.read(new File(templateImgPath));
		Graphics g = buffImage.getGraphics();
		Font font = new Font("黑体",Font.BOLD,15);
		g.setColor( new Color(30,27,27));
		g.setFont(font);
		g.drawString(merchantName,30,70);
		
		Font fontCardNumber = new Font("黑体",Font.BOLD,15);
		g.setColor( new Color(30,27,27));
		g.setFont(fontCardNumber);
		g.drawString(cardNumber,190,170);
	
		FileOutputStream outImg = new FileOutputStream(new File(newImgPath));
		ImageIO.write(buffImage,"jpg", outImg);
		outImg.flush();
		outImg.close();
	}
	
	/**
	 * 画卡的正面 
	 * @throws IOException 
	 */
	public static void drawSide2( String templateImgPath,String memberName,String newImgPath ) throws IOException{
		BufferedImage buffImage =  ImageIO.read(new File(templateImgPath));
		Graphics g = buffImage.getGraphics();
		Font font = new Font("黑体",Font.BOLD,15);
		g.setColor( new Color(30,27,27));
		g.setFont(font);
		g.drawString(memberName,30,70);
	
		FileOutputStream outImg = new FileOutputStream(new File(newImgPath));
		ImageIO.write(buffImage,"jpg", outImg);
		outImg.flush();
		outImg.close();
	}
	
}
