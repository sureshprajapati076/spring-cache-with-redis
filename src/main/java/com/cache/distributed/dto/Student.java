package com.cache.distributed.dto;

import java.io.Serializable;

public record Student(Integer id, String name, Integer age) implements Serializable {
}
