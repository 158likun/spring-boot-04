package com.atguigu.springboot04.service;

import com.atguigu.springboot04.bean.Words;
import com.atguigu.springboot04.mapper.WordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordsServiceImpl implements WordsService {
    @Autowired
    private WordsMapper wordsMapper;
    public int insert(Words words){
        return wordsMapper.insert(words);
    }
    public List<Words> getWordsByNumberg(int numberg){
        return wordsMapper.getWordsByNumberg(numberg);
    }
    public List<Words> getWords(int numberg){
        return wordsMapper.getWords(numberg);
    }
    public int getCount(int numberg){
        return wordsMapper.getCount(numberg);
    }
    public int update(int number){
        return wordsMapper.update(number);
    }
    public Words getWord(int numberw){
        return wordsMapper.getWord(numberw);
    }
}
