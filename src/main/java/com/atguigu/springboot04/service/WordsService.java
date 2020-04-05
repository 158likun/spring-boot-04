package com.atguigu.springboot04.service;

import com.atguigu.springboot04.bean.Words;
import com.atguigu.springboot04.mapper.WordsMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface WordsService {
    int insert(Words words);
    List<Words> getWordsByNumberg(int numberg);
    List<Words> getWords(int numberg);
    int getCount(int numberg);
    int update(int number);
    Words getWord(int numberw);
}
