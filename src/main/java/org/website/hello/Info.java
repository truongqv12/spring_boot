package org.website.hello;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TẠo ra class này chỉ để lưu giữ thông tin
 */
@Data
@AllArgsConstructor
public class Info {
    String key;
    String value;
}
