package com.services.ResultService;

import dao.NumbersResultDataDao;
import schemas.NumbersResultData;
import schemas.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class NumberResultService extends ResultService {
    NumbersResultDataDao resultDataDao;

    public NumberResultService(EntityManager em) {
        super(em);

        resultDataDao = new NumbersResultDataDao(em);
    }

    @Override
    public Optional<Result> loadResult(int resultId) {
        var rawResultData = resultDataDao.getById(resultId);
        if (rawResultData.isEmpty()) {
            return Optional.empty();
        }
        var resultData = getResultData(rawResultData);
        var result = createResult(resultData, rawResultData);

        return Optional.of(result);
    }

    private Result createResult(ArrayList<ResultData> resultData, ArrayList<NumbersResultData> rawResultData) {
        Result result = new Result();
        var numbersResult = rawResultData.get(0).getNumbersResult();

        User user = rawResultData.get(0).getNumbersResult().getUser();

        result.setId(rawResultData.get(0).getId());
        result.setUsername(user.getUsername());

        result.setGrade(numbersResult.getGrade());
        result.setDataCount(resultData.size());

        result.setStartTime(numbersResult.getDateTime());
        result.setMemorizeTime(getMemorizeTime(resultData));
        result.setRememberTime(numbersResult.getRememberTime());

        result.setMinMemorizeTime(getMinMemorizeTime(resultData));
        result.setMaxMemorizeTime(getMaxMemorizeTime(resultData));
        result.setAvgMemorizeTime(getAvgMemorizeTime(resultData));

        result.setCorrectAns(getCorrectAns(resultData));

        result.setData(resultData);

        return result;
    }

    private ArrayList<ResultData> getResultData(ArrayList<NumbersResultData> rawResultData) {

        return rawResultData.stream().map((item) -> {
            ResultData resultData = new ResultData();

            resultData.setValue(Integer.toString(item.getNumber()));
            resultData.setAnswer(Integer.toString(item.getAnswer()));
            resultData.setTime(item.getTime());
            resultData.setIndex(item.getDataId() + 1);

            return resultData;
        }).collect(Collectors.toCollection(ArrayList::new));

    }

}
