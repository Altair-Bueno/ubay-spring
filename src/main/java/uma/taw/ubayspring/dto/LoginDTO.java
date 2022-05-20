package uma.taw.ubayspring.dto;

import uma.taw.ubayspring.types.KindEnum;

public record LoginDTO(Integer id, String username, KindEnum kindEnum) {}
