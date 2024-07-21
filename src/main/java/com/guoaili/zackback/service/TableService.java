package com.guoaili.zackback.service;

import java.util.List;

import com.guoaili.zackback.DTO.ExamUpdVo;
import com.guoaili.zackback.DTO.ExtensionUpdVo;
import com.guoaili.zackback.DTO.NotebookUpdVo;
import com.guoaili.zackback.DTO.ReviewUpdVo;
import com.guoaili.zackback.DTO.WritingUpdVo;
import com.guoaili.zackback.DTO.WrongUpdVo;
import com.guoaili.zackback.entity.ExamEntity;
import com.guoaili.zackback.entity.ExtensionEntity;
import com.guoaili.zackback.entity.NotebookEntity;
import com.guoaili.zackback.entity.ReviewEntity;
import com.guoaili.zackback.entity.WritingEntity;
import com.guoaili.zackback.entity.WrongEntity;

public interface TableService {

    List<WritingEntity> getAllWriting(String subject);
    List<NotebookEntity> getAllNotebook(String subject);
    List<ExamEntity> getAllExam(String subject);
    List<ReviewEntity> getAllReview(String subject);
    List<WrongEntity> getAllWrong(String subject);
    List<ExtensionEntity> getAllExtension(String subject);

    void deleteOneWriting(long id);
    void deleteOneNotebook(long id);
    void deleteOneExam(long id);
    void deleteOneReview(long id);
    void deleteOneWrong(long id);
    void deleteOneExtension(long id);
    // 2024/7/1
    void updateOneWriting(WritingUpdVo wuv);
    void updateOneExtension(ExtensionUpdVo wuv);
    void updateOneNotebook(NotebookUpdVo wuv);
    void updateOneExam(ExamUpdVo wuv);
    void updateOneReview(ReviewUpdVo wuv);
    void updateOneWrong(WrongUpdVo wuv);
}
