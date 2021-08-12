package com.shiyq.wuye.entity.DTO;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class LicencePlateDTO {

    private List<HashMap<String, String>> words_result;

    private String log_id;

    private String words_result_num;

}
