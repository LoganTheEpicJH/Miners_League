package com.minersleague.main.towerdefense;

public enum BlockMetaData {
	
	OAK_LOG_UP(0),
	OAK_LOG_X(4),
	OAK_LOG_Z(8),
	OAK_LOG_NONE(12),
	SPRUCE_LOG_UP(1),
	SPRUCE_LOG_X(5),
	SPRUCE_LOG_Z(9),
	SPRUCE_LOG_NONE(13),
	BIRCH_LOG_UP(2),
	BIRCH_LOG_X(6),
	BIRCH_LOG_Z(10),
	BIRCH_LOG_NONE(14),
	JUNGLE_LOG_UP(3),
	JUNGLE_LOG_X(7),
	JUNGLE_LOG_Z(11),
	JUNGLE_LOG_NONE(15),
	ACACIA_LOG_UP(0),
	ACACIA_LOG_X(4),
	ACACIA_LOG_Z(8),
	ACACIA_LOG_NONE(12),
	DARK_OAK_LOG_UP(1),
	DARK_OAK_LOG_X(5),
	DARK_OAK_LOG_Z(9),
	DARK_OAK_LOG_NONE(13),
	END_ROD_DOWN(0),
	END_ROD_UP(1),
	END_ROD_NORTH(2),
	END_ROD_EAST(5),
	END_ROD_SOUTH(3),
	END_ROD_WEST(4),
	STAIR_NORTH(3),
	STAIR_EAST(0),
	STAIR_SOUTH(2),
	STAIR_WEST(1),
	UPSIDE_STAIR_NORTH(7),
	UPSIDE_STAIR_EAST(4),
	UPSIDE_STAIR_SOUTH(6),
	UPSIDE_STAIR_WEST(5),
	COLOR_WHITE(0),
	COLOR_ORANGE(1),
	COLOR_MAGENTA(2),
	COLOR_LIGHT_BLUE(3),
	COLOR_YELLOW(4),
	COLOR_LIME(5),
	COLOR_PINK(6),
	COLOR_GRAY(7),
	COLOR_LIGHT_GRAY(8),
	COLOR_CYAN(9),
	COLOR_PURPLE(10),
	COLOR_BLUE(11),
	COLOR_BROWN(12),
	COLOR_GREEN(13),
	COLOR_RED(14),
	COLOR_BLACK(15),
	BYTE_1(1),
	BYTE_2(2),
	BYTE_3(3),
	BYTE_4(4),
	BYTE_5(5),
	BYTE_6(6),
	BYTE_7(7),
	BYTE_8(9),
	BYTE_9(9),
	BYTE_10(10),
	BYTE_11(11),
	BYTE_12(12),
	BYTE_13(13),
	BYTE_14(14),
	BYTE_15(15),
	BYTE_16(16),
	BYTE_17(17),
	BYTE_18(18),
	BYTE_19(19),
	BYTE_20(20),
	BYTE_21(21),
	BYTE_22(22),
	BYTE_23(23),
	BYTE_24(24),
	BYTE_25(25),
	BYTE_26(26),
	BYTE_27(27),
	BYTE_28(28),
	BYTE_29(29),
	BYTE_30(30),
	BYTE_31(31),
	BYTE_32(32);

	private int meta;
	
	BlockMetaData(int meta) {
		this.meta = meta;
	}
	
	public int setSpecialData(int i) {
		return i;
	}
	
	public int getMeta() {
		return meta;
	}
	
}