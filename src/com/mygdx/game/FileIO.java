package com.mygdx.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class FileIO {
    public boolean writeFile(String path, String text){
        try {
            FileHandle file = Gdx.files.local(path);
            file.writeString(text, false);              
		} catch (GdxRuntimeException e) {
			e.printStackTrace();
            return false;
		}
        return true;
    }

    public String readFile(String path){
        String text2 = "";
        try {
            FileHandle file = Gdx.files.local(path);
            text2 = file.readString();
        } catch (GdxRuntimeException e) {
            e.printStackTrace();
            text2 = "";
        }
        return text2;
    }

    public boolean appendFile(String path, String text){
        try {
            FileHandle file = Gdx.files.local(path);
            file.writeString(text, true);              
		} catch (GdxRuntimeException e) {
			e.printStackTrace();
            return false;
		}
        return true;
    }
}
