package com.atguigu.springboot04.mapper;

import com.atguigu.springboot04.bean.Words;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WordsMapper {
    int insert(Words words);
    List<Words> getWordsByNumberg(int numberg);
    List<Words> getWords(int numberg);
    int getCount(int numberg);
    int update(int number);
    Words getWord(int numberw);
}
