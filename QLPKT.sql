USE [master]
GO
/****** Object:  Database [QLPKT]    Script Date: 11/21/2019 20:58:31 ******/
CREATE DATABASE [QLPKT] ON  PRIMARY 
( NAME = N'QLPKT', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\QLPKT.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'QLPKT_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\QLPKT_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [QLPKT] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QLPKT].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QLPKT] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [QLPKT] SET ANSI_NULLS OFF
GO
ALTER DATABASE [QLPKT] SET ANSI_PADDING OFF
GO
ALTER DATABASE [QLPKT] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [QLPKT] SET ARITHABORT OFF
GO
ALTER DATABASE [QLPKT] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [QLPKT] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [QLPKT] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [QLPKT] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [QLPKT] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [QLPKT] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [QLPKT] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [QLPKT] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [QLPKT] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [QLPKT] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [QLPKT] SET  DISABLE_BROKER
GO
ALTER DATABASE [QLPKT] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [QLPKT] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [QLPKT] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [QLPKT] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [QLPKT] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [QLPKT] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [QLPKT] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [QLPKT] SET  READ_WRITE
GO
ALTER DATABASE [QLPKT] SET RECOVERY FULL
GO
ALTER DATABASE [QLPKT] SET  MULTI_USER
GO
ALTER DATABASE [QLPKT] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [QLPKT] SET DB_CHAINING OFF
GO
EXEC sys.sp_db_vardecimal_storage_format N'QLPKT', N'ON'
GO
USE [QLPKT]
GO
/****** Object:  Table [dbo].[LOP]    Script Date: 11/21/2019 20:58:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOP](
	[MALOP] [nchar](10) NOT NULL,
	[TENLOP] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_LOP] PRIMARY KEY CLUSTERED 
(
	[MALOP] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_LOP] UNIQUE NONCLUSTERED 
(
	[TENLOP] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[LOP] ([MALOP], [TENLOP]) VALUES (N'D17CQCN01 ', N'ĐHCQ- Ngành CNTT- 2017-1')
INSERT [dbo].[LOP] ([MALOP], [TENLOP]) VALUES (N'D17CQCN02 ', N'ĐHCQ- Ngành CNTT- 2017-2')
INSERT [dbo].[LOP] ([MALOP], [TENLOP]) VALUES (N'D17CQCN03 ', N'ĐHCQ- Ngành CNTT- 2017-3')
INSERT [dbo].[LOP] ([MALOP], [TENLOP]) VALUES (N'D17CQDT01 ', N'ĐHCQ- Ngành ĐT- 2017-1')
INSERT [dbo].[LOP] ([MALOP], [TENLOP]) VALUES (N'D17CQDT02 ', N'ĐHCQ- Ngành ĐT- 2017-2')
INSERT [dbo].[LOP] ([MALOP], [TENLOP]) VALUES (N'D17CQKT01 ', N'ĐHCQ- Ngành KT- 2017-1')
INSERT [dbo].[LOP] ([MALOP], [TENLOP]) VALUES (N'D17CQMT01 ', N'ĐHCQ- Ngành MMT- 2017-1')
INSERT [dbo].[LOP] ([MALOP], [TENLOP]) VALUES (N'D18CQQT01 ', N'ĐHCQ- Ngành QTKD- 2017-1')
/****** Object:  Table [dbo].[LOAINV]    Script Date: 11/21/2019 20:58:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOAINV](
	[MALOAI] [nchar](10) NOT NULL,
	[TENLOAI] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_LOAINV] PRIMARY KEY CLUSTERED 
(
	[MALOAI] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [UK_LOAINV] UNIQUE NONCLUSTERED 
(
	[TENLOAI] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[LOAINV] ([MALOAI], [TENLOAI]) VALUES (N'GVHD      ', N'Giảng viên hợp đồng')
INSERT [dbo].[LOAINV] ([MALOAI], [TENLOAI]) VALUES (N'GVTG      ', N'Giảng viên thỉnh giảng')
INSERT [dbo].[LOAINV] ([MALOAI], [TENLOAI]) VALUES (N'NVHD      ', N'Nhân viên hợp đồng')
INSERT [dbo].[LOAINV] ([MALOAI], [TENLOAI]) VALUES (N'NVK       ', N'Nhân viên khoán')
/****** Object:  Table [dbo].[LOAIDONGIA]    Script Date: 11/21/2019 20:58:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOAIDONGIA](
	[MALOAIDG] [nchar](10) NOT NULL,
	[TENDG] [nvarchar](50) NULL,
 CONSTRAINT [PK_LOAIDONGIA] PRIMARY KEY CLUSTERED 
(
	[MALOAIDG] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[LOAIDONGIA] ([MALOAIDG], [TENDG]) VALUES (N'CHAMTHI   ', N'Chấm thi')
INSERT [dbo].[LOAIDONGIA] ([MALOAIDG], [TENDG]) VALUES (N'COITHI    ', N'Coi thi')
INSERT [dbo].[LOAIDONGIA] ([MALOAIDG], [TENDG]) VALUES (N'RADE      ', N'Ra đề thi')
/****** Object:  Table [dbo].[MON]    Script Date: 11/21/2019 20:58:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MON](
	[MAMON] [nchar](10) NOT NULL,
	[TENMON] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_MON] PRIMARY KEY CLUSTERED 
(
	[MAMON] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1102   ', N'Đường lối cách mạng Đảng cộng sản VN')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1105   ', N'Giáo dục Quốc phòng')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1106   ', N'Giáo dục thể chất 1')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1107   ', N'Giáo dục thể chất 2')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1111   ', N'Những nguyên lý cơ bản của chủ nghĩa Mác-Lênin 1')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1112   ', N'Những nguyên lý cơ bản của chủ nghĩa Mác-Lênin 2')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1122   ', N'Tư tưởng Hồ Chí Minh')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1141   ', N'Tiếng anh A1.1')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1142   ', N'Tiếng anh A1.2')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1143   ', N'Tiếng anh A2.1')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1144   ', N'Tiếng anh A2.2')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1145   ', N'Tiếng anh B1.1')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1146   ', N'Tiếng Anh B1.2')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1201   ', N'Đại số')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1203   ', N'Giải tích 1')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1204   ', N'Giải tích 2')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1224   ', N'Vật lý 1 và thí nghiệm')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1226   ', N'Xác suất thống kê')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'BAS1227   ', N'Vật lý 3 và thí nghiệm')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'ELE1319   ', N'Lý thuyết thông tin')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'ELE1330   ', N'Xử lý tín hiệu số')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'ELE1433   ', N'Kỹ thuật số')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1154   ', N'Tin học cơ sở 1')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1155   ', N'Tin học cơ sở 2')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1303   ', N'An toàn và bảo mật hệ thống thông tin')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1306   ', N'Cấu trúc dữ liệu và giải thuật')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1313   ', N'Cơ sở dữ liệu')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1319   ', N'Hệ điều hành')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1323   ', N'Kiến trúc máy tính')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1328   ', N'Kỹ thuật đồ họa')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1330   ', N'Kỹ thuật vi xử lý')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1332   ', N'Lập trình hướng đối tượng')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1336   ', N'Mạng máy tính')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1339   ', N'Ngôn ngữ lập trình C++')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1340   ', N'Nhập môn công nghệ phần mềm')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1341   ', N'Nhập môn trí tuệ nhân tạo')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1358   ', N'Toán rời rạc 1')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1359-3 ', N'Toán rời rạc 2')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'INT1362   ', N'Xử lý ảnh')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'SKD1101   ', N'Kỹ năng thuyết trình')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'SKD1102   ', N'Kỹ năng làm việc nhóm')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'SKD1103   ', N'Kỹ năng tạo lập Văn bản')
INSERT [dbo].[MON] ([MAMON], [TENMON]) VALUES (N'SKD1108   ', N'Phương pháp luận nghiên cứu khoa học')
/****** Object:  Table [dbo].[PHONG]    Script Date: 11/21/2019 20:58:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHONG](
	[MAPHONG] [nchar](10) NOT NULL,
	[DAYNHA] [nchar](10) NULL,
 CONSTRAINT [PK_PHONG] PRIMARY KEY CLUSTERED 
(
	[MAPHONG] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2A08      ', N'A         ')
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2A16      ', N'A         ')
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2A22      ', N'A         ')
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2A23      ', N'A         ')
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2A2425    ', N'A         ')
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2A26      ', N'A         ')
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2A27      ', N'A         ')
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2A32      ', N'A         ')
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2A33      ', N'A         ')
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2A34      ', N'A         ')
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2A35      ', N'A         ')
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2A36      ', N'A         ')
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2B12      ', N'B         ')
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2B13      ', N'B         ')
INSERT [dbo].[PHONG] ([MAPHONG], [DAYNHA]) VALUES (N'2B14      ', N'B         ')
/****** Object:  Table [dbo].[PBKHOA]    Script Date: 11/21/2019 20:58:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PBKHOA](
	[MAPBK] [nchar](10) NOT NULL,
	[TENPBK] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_PBKHOA] PRIMARY KEY CLUSTERED 
(
	[MAPBK] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [UK_PBK] UNIQUE NONCLUSTERED 
(
	[TENPBK] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[PBKHOA] ([MAPBK], [TENPBK]) VALUES (N'COBAN     ', N'Khoa cơ bản 2')
INSERT [dbo].[PBKHOA] ([MAPBK], [TENPBK]) VALUES (N'CNTT2     ', N'Khoa công nghệ thông tin 2')
INSERT [dbo].[PBKHOA] ([MAPBK], [TENPBK]) VALUES (N'DT2       ', N'Khoa điện tử 2')
INSERT [dbo].[PBKHOA] ([MAPBK], [TENPBK]) VALUES (N'QTKD2     ', N'Khoa quản trị kinh doanh 2')
INSERT [dbo].[PBKHOA] ([MAPBK], [TENPBK]) VALUES (N'VT2       ', N'Khoa viễn thông 2')
INSERT [dbo].[PBKHOA] ([MAPBK], [TENPBK]) VALUES (N'CSVC      ', N'Phòng cơ sở vật chất')
INSERT [dbo].[PBKHOA] ([MAPBK], [TENPBK]) VALUES (N'CTSV      ', N'Phòng công tác sinh viên')
INSERT [dbo].[PBKHOA] ([MAPBK], [TENPBK]) VALUES (N'GV        ', N'Phòng giáo vụ')
INSERT [dbo].[PBKHOA] ([MAPBK], [TENPBK]) VALUES (N'HC        ', N'Phòng hành chính')
INSERT [dbo].[PBKHOA] ([MAPBK], [TENPBK]) VALUES (N'KT        ', N'Phòng khảo thí')
/****** Object:  Table [dbo].[TOBOMON]    Script Date: 11/21/2019 20:58:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TOBOMON](
	[MATBM] [nchar](10) NOT NULL,
	[TENTBM] [nvarchar](50) NOT NULL,
	[MAPBK] [nchar](10) NOT NULL,
 CONSTRAINT [PK_TOBOMON] PRIMARY KEY CLUSTERED 
(
	[MATBM] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [UK_TBM] UNIQUE NONCLUSTERED 
(
	[TENTBM] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[TOBOMON] ([MATBM], [TENTBM], [MAPBK]) VALUES (N'CNPM      ', N'Công nghệ phần mềm', N'CNTT2     ')
INSERT [dbo].[TOBOMON] ([MATBM], [TENTBM], [MAPBK]) VALUES (N'ENG       ', N'Tiếng Anh', N'COBAN     ')
INSERT [dbo].[TOBOMON] ([MATBM], [TENTBM], [MAPBK]) VALUES (N'MGMT      ', N'Mạng máy tính', N'cntt2     ')
INSERT [dbo].[TOBOMON] ([MATBM], [TENTBM], [MAPBK]) VALUES (N'SUACHUA   ', N'Tổ sữa chữa', N'CSVC      ')
INSERT [dbo].[TOBOMON] ([MATBM], [TENTBM], [MAPBK]) VALUES (N'TAPVU     ', N'Tổ tạp vụ', N'csvc      ')
INSERT [dbo].[TOBOMON] ([MATBM], [TENTBM], [MAPBK]) VALUES (N'TOAN      ', N'Toán kỹ thuật', N'COBAN     ')
INSERT [dbo].[TOBOMON] ([MATBM], [TENTBM], [MAPBK]) VALUES (N'VATLY     ', N'Vật lý kỹ thuật', N'COBAN     ')
/****** Object:  Table [dbo].[CTDONGIA]    Script Date: 11/21/2019 20:58:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTDONGIA](
	[NGAY] [date] NOT NULL,
	[MALOAIDG] [nchar](10) NOT NULL,
	[MAMON] [nchar](10) NOT NULL,
	[GIA] [money] NOT NULL,
 CONSTRAINT [PK_CTDONGIA_1] PRIMARY KEY CLUSTERED 
(
	[NGAY] ASC,
	[MALOAIDG] ASC,
	[MAMON] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CTDONGIA] ([NGAY], [MALOAIDG], [MAMON], [GIA]) VALUES (CAST(0x66400B00 AS Date), N'RADE      ', N'INT1332   ', 65000.0000)
INSERT [dbo].[CTDONGIA] ([NGAY], [MALOAIDG], [MAMON], [GIA]) VALUES (CAST(0x88400B00 AS Date), N'RADE      ', N'BAS1146   ', 90000.0000)
INSERT [dbo].[CTDONGIA] ([NGAY], [MALOAIDG], [MAMON], [GIA]) VALUES (CAST(0x89400B00 AS Date), N'CHAMTHI   ', N'BAS1146   ', 8000.0000)
INSERT [dbo].[CTDONGIA] ([NGAY], [MALOAIDG], [MAMON], [GIA]) VALUES (CAST(0x89400B00 AS Date), N'COITHI    ', N'BAS1146   ', 66000.0000)
INSERT [dbo].[CTDONGIA] ([NGAY], [MALOAIDG], [MAMON], [GIA]) VALUES (CAST(0x89400B00 AS Date), N'RADE      ', N'BAS1145   ', 60000.0000)
INSERT [dbo].[CTDONGIA] ([NGAY], [MALOAIDG], [MAMON], [GIA]) VALUES (CAST(0x89400B00 AS Date), N'RADE      ', N'ELE1433   ', 56000.0000)
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 11/21/2019 20:58:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MANV] [nchar](10) NOT NULL,
	[HO] [nvarchar](50) NOT NULL,
	[TEN] [nvarchar](20) NOT NULL,
	[GIOITINH] [nvarchar](3) NOT NULL,
	[EMAIL] [nvarchar](50) NULL,
	[DIACHI] [nvarchar](50) NULL,
	[MALOAI] [nchar](10) NOT NULL,
	[MATBM] [nchar](10) NOT NULL,
 CONSTRAINT [PK_NHANVIEN_1] PRIMARY KEY CLUSTERED 
(
	[MANV] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [CK_MANV] UNIQUE NONCLUSTERED 
(
	[MANV] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [GIOITINH], [EMAIL], [DIACHI], [MALOAI], [MATBM]) VALUES (N'GV01      ', N'Trần Văn', N'An', N'nam', NULL, NULL, N'GVHD      ', N'TOAN      ')
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [GIOITINH], [EMAIL], [DIACHI], [MALOAI], [MATBM]) VALUES (N'GV02      ', N'Nguyễn Thị', N'Bình', N'Nữ', NULL, NULL, N'GVTG      ', N'ENG       ')
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [GIOITINH], [EMAIL], [DIACHI], [MALOAI], [MATBM]) VALUES (N'GV03      ', N'Jeon Jung', N'Kook', N'Nam', NULL, NULL, N'GVTG      ', N'ENG       ')
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [GIOITINH], [EMAIL], [DIACHI], [MALOAI], [MATBM]) VALUES (N'GV04      ', N'Nguyễn Thu ', N'Thủy', N'Nữ', NULL, NULL, N'GVHD      ', N'CNPM      ')
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [GIOITINH], [EMAIL], [DIACHI], [MALOAI], [MATBM]) VALUES (N'GV05      ', N'Trịnh', N'Minh Hằng', N'Nữ', N'ttt@gmail.com', N'', N'GVTG      ', N'MGMT      ')
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [GIOITINH], [EMAIL], [DIACHI], [MALOAI], [MATBM]) VALUES (N'NV01      ', N'Đặng Thái', N'Cường', N'Nam', NULL, NULL, N'NVHD      ', N'SUACHUA   ')
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [GIOITINH], [EMAIL], [DIACHI], [MALOAI], [MATBM]) VALUES (N'NV02      ', N'Đinh Việt', N'Toàn', N'Nam', NULL, NULL, N'NVK       ', N'TAPVU     ')
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [GIOITINH], [EMAIL], [DIACHI], [MALOAI], [MATBM]) VALUES (N'NV03      ', N'Đặng', N'Văn  Lâm', N'Nữ', N'', N'', N'NVK       ', N'SUACHUA   ')
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [GIOITINH], [EMAIL], [DIACHI], [MALOAI], [MATBM]) VALUES (N'NV04      ', N'Nguyễn Tuấn ', N'Mạnh', N'Nam', NULL, NULL, N'NVHD      ', N'SUACHUA   ')
INSERT [dbo].[NHANVIEN] ([MANV], [HO], [TEN], [GIOITINH], [EMAIL], [DIACHI], [MALOAI], [MATBM]) VALUES (N'NV05      ', N'Võ Hoàng ', N'Yến', N'Nữ', NULL, NULL, N'NVK       ', N'TAPVU     ')
/****** Object:  Table [dbo].[LOPTC]    Script Date: 11/21/2019 20:58:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOPTC](
	[MALOPTC] [int] IDENTITY(1,1) NOT NULL,
	[MAMON] [nchar](10) NOT NULL,
	[NHOM] [int] NOT NULL,
	[NIENKHOA] [nchar](10) NOT NULL,
	[HOCKY] [int] NOT NULL,
	[SLDANGKY] [int] NOT NULL,
	[MALOP] [nchar](10) NOT NULL,
	[MANV] [nchar](10) NOT NULL,
 CONSTRAINT [PK_LOPTC] PRIMARY KEY CLUSTERED 
(
	[MAMON] ASC,
	[NHOM] ASC,
	[NIENKHOA] ASC,
	[HOCKY] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [UK_MATC] UNIQUE NONCLUSTERED 
(
	[MALOPTC] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[LOPTC] ON
INSERT [dbo].[LOPTC] ([MALOPTC], [MAMON], [NHOM], [NIENKHOA], [HOCKY], [SLDANGKY], [MALOP], [MANV]) VALUES (1, N'BAS1102   ', 1, N'2019      ', 1, 40, N'D17CQMT01 ', N'GV05      ')
INSERT [dbo].[LOPTC] ([MALOPTC], [MAMON], [NHOM], [NIENKHOA], [HOCKY], [SLDANGKY], [MALOP], [MANV]) VALUES (2, N'BAS1111   ', 4, N'2019      ', 1, 50, N'D17CQKT01 ', N'GV04      ')
INSERT [dbo].[LOPTC] ([MALOPTC], [MAMON], [NHOM], [NIENKHOA], [HOCKY], [SLDANGKY], [MALOP], [MANV]) VALUES (3, N'BAS1146   ', 1, N'2019      ', 1, 90, N'D17CQCN03 ', N'GV01      ')
INSERT [dbo].[LOPTC] ([MALOPTC], [MAMON], [NHOM], [NIENKHOA], [HOCKY], [SLDANGKY], [MALOP], [MANV]) VALUES (4, N'BAS1203   ', 3, N'2019      ', 1, 50, N'D17CQDT02 ', N'GV03      ')
INSERT [dbo].[LOPTC] ([MALOPTC], [MAMON], [NHOM], [NIENKHOA], [HOCKY], [SLDANGKY], [MALOP], [MANV]) VALUES (5, N'INT1155   ', 2, N'2019      ', 1, 80, N'D18CQQT01 ', N'GV02      ')
INSERT [dbo].[LOPTC] ([MALOPTC], [MAMON], [NHOM], [NIENKHOA], [HOCKY], [SLDANGKY], [MALOP], [MANV]) VALUES (6, N'INT1332   ', 3, N'2019      ', 1, 75, N'D17CQCN01 ', N'GV01      ')
SET IDENTITY_INSERT [dbo].[LOPTC] OFF
/****** Object:  StoredProcedure [dbo].[SP_LOAD_NV_TEN_MANV]    Script Date: 11/21/2019 20:58:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LOAD_NV_TEN_MANV]
@MATBM NVARCHAR(20)
AS
SELECT HO + ' ' + TEN, MANV FROM NHANVIEN WHERE NHANVIEN.MATBM = @MATBM
--
GO
/****** Object:  StoredProcedure [dbo].[SP_LOAD_NV_TBM]    Script Date: 11/21/2019 20:58:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LOAD_NV_TBM]
@MATBM NVARCHAR(20)
AS
SELECT  HO + ' ' + TEN AS'HOTEN', MANV FROM NHANVIEN WHERE NHANVIEN.MATBM = @MATBM
EXEC SP_LOAD_NV_TBM'ENG'
--
GO
/****** Object:  View [dbo].[V_NHANVIEN]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[V_NHANVIEN]
AS
SELECT     dbo.NHANVIEN.MANV, dbo.NHANVIEN.HO + ' ' + dbo.NHANVIEN.TEN AS HOTEN, dbo.NHANVIEN.GIOITINH AS SEX, dbo.PBKHOA.TENPBK, dbo.NHANVIEN.EMAIL, dbo.NHANVIEN.DIACHI, 
                      dbo.LOAINV.TENLOAI AS LOAINV, dbo.TOBOMON.TENTBM
FROM         dbo.NHANVIEN INNER JOIN
                      dbo.TOBOMON ON dbo.NHANVIEN.MATBM = dbo.TOBOMON.MATBM INNER JOIN
                      dbo.PBKHOA ON dbo.TOBOMON.MAPBK = dbo.PBKHOA.MAPBK INNER JOIN
                      dbo.LOAINV ON dbo.NHANVIEN.MALOAI = dbo.LOAINV.MALOAI
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "NHANVIEN"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 126
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "TOBOMON"
            Begin Extent = 
               Top = 6
               Left = 434
               Bottom = 111
               Right = 594
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "PBKHOA"
            Begin Extent = 
               Top = 6
               Left = 236
               Bottom = 96
               Right = 396
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "LOAINV"
            Begin Extent = 
               Top = 112
               Left = 268
               Bottom = 202
               Right = 428
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 20
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 117' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'V_NHANVIEN'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane2', @value=N'0
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'V_NHANVIEN'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'V_NHANVIEN'
GO
/****** Object:  StoredProcedure [dbo].[SP_CO_MANV_TIM_TBM]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[SP_CO_MANV_TIM_TBM]
@MANV NVARCHAR(20),
@MALTC NVARCHAR(20)
AS
SELECT HO, TEN, MANV, MATBM INTO #NV FROM NHANVIEN WHERE NHANVIEN.MANV = @MANV
SELECT MALOPTC, TENMON, NIENKHOA, HOCKY, NHOM INTO #TC FROM LOPTC, MON WHERE MON.MAMON = LOPTC.MAMON
SELECT HO, TEN, #NV.MANV, TENPBK, TENTBM, #TC.*  FROM #NV, PBKHOA, TOBOMON, #TC
WHERE #NV.MATBM = TOBOMON.MATBM AND TOBOMON.MAPBK = PBKHOA.MAPBK AND #TC.MALOPTC = @MALTC
--
GO
/****** Object:  View [dbo].[V_LTC]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[V_LTC]
AS
SELECT LOPTC.MALOPTC,LOPTC.NHOM, LOPTC.NIENKHOA, LOPTC.HOCKY, MON.TENMON FROM LOPTC, MON WHERE LOPTC.MAMON = MON.MAMON

---
GO
/****** Object:  View [dbo].[V_LOPTC]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[V_LOPTC]
AS
SELECT     dbo.MON.TENMON, dbo.LOPTC.NIENKHOA, dbo.LOPTC.HOCKY, dbo.LOPTC.NHOM, dbo.LOPTC.MALOPTC, dbo.NHANVIEN.HO + ' ' + dbo.NHANVIEN.TEN AS 'HOTEN'
FROM         dbo.LOPTC INNER JOIN
                      dbo.MON ON dbo.LOPTC.MAMON = dbo.MON.MAMON INNER JOIN
                      dbo.NHANVIEN ON dbo.LOPTC.MANV = dbo.NHANVIEN.MANV
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "LOPTC"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 126
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 4
         End
         Begin Table = "MON"
            Begin Extent = 
               Top = 6
               Left = 236
               Bottom = 96
               Right = 396
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "NHANVIEN"
            Begin Extent = 
               Top = 6
               Left = 434
               Bottom = 126
               Right = 594
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'V_LOPTC'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'V_LOPTC'
GO
/****** Object:  Table [dbo].[DETHI]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DETHI](
	[MANV] [nchar](10) NOT NULL,
	[MALOPTC] [int] NOT NULL,
	[SOLUONGDE] [int] NOT NULL,
	[NGAY] [date] NULL,
 CONSTRAINT [PK_DETHI_1] PRIMARY KEY CLUSTERED 
(
	[MANV] ASC,
	[MALOPTC] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[DETHI] ([MANV], [MALOPTC], [SOLUONGDE], [NGAY]) VALUES (N'GV01      ', 6, 7, NULL)
/****** Object:  Table [dbo].[CTLICHTHI]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTLICHTHI](
	[MAPHONG] [nchar](10) NOT NULL,
	[NGAYTHI] [date] NOT NULL,
	[TIETBATDAU] [int] NOT NULL,
	[MALOPTC] [int] NOT NULL,
	[SLDUTHI] [int] NOT NULL,
	[DOTTHI] [int] NOT NULL,
 CONSTRAINT [PK_CTLICHTHI] PRIMARY KEY CLUSTERED 
(
	[MAPHONG] ASC,
	[NGAYTHI] ASC,
	[TIETBATDAU] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_CTLICHTHI] UNIQUE NONCLUSTERED 
(
	[MALOPTC] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CTLICHTHI] ([MAPHONG], [NGAYTHI], [TIETBATDAU], [MALOPTC], [SLDUTHI], [DOTTHI]) VALUES (N'2A08      ', CAST(0x7D3F0B00 AS Date), 1, 3, 90, 1)
INSERT [dbo].[CTLICHTHI] ([MAPHONG], [NGAYTHI], [TIETBATDAU], [MALOPTC], [SLDUTHI], [DOTTHI]) VALUES (N'2A08      ', CAST(0x34400B00 AS Date), 5, 1, 6, 2)
INSERT [dbo].[CTLICHTHI] ([MAPHONG], [NGAYTHI], [TIETBATDAU], [MALOPTC], [SLDUTHI], [DOTTHI]) VALUES (N'2A16      ', CAST(0x233F0B00 AS Date), 1, 4, 60, 1)
INSERT [dbo].[CTLICHTHI] ([MAPHONG], [NGAYTHI], [TIETBATDAU], [MALOPTC], [SLDUTHI], [DOTTHI]) VALUES (N'2B12      ', CAST(0x233F0B00 AS Date), 1, 6, 75, 2)
INSERT [dbo].[CTLICHTHI] ([MAPHONG], [NGAYTHI], [TIETBATDAU], [MALOPTC], [SLDUTHI], [DOTTHI]) VALUES (N'2B13      ', CAST(0x34400B00 AS Date), 1, 5, 50, 1)
/****** Object:  Table [dbo].[BAITHI]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BAITHI](
	[MANV] [nchar](10) NOT NULL,
	[MALOPTC] [int] NOT NULL,
	[SOLUONGBAITHI] [int] NOT NULL,
	[NGAY] [date] NULL,
 CONSTRAINT [PK_BAITHI] PRIMARY KEY CLUSTERED 
(
	[MANV] ASC,
	[MALOPTC] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CBCOITHI]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CBCOITHI](
	[NGAYTHI] [date] NOT NULL,
	[TIETBATDAU] [int] NOT NULL,
	[MAPHONG] [nchar](10) NOT NULL,
	[MANV] [nchar](10) NOT NULL,
 CONSTRAINT [PK_CBCOITHI] PRIMARY KEY CLUSTERED 
(
	[MANV] ASC,
	[NGAYTHI] ASC,
	[TIETBATDAU] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CBCOITHI] ([NGAYTHI], [TIETBATDAU], [MAPHONG], [MANV]) VALUES (CAST(0x233F0B00 AS Date), 1, N'2A16      ', N'GV01      ')
INSERT [dbo].[CBCOITHI] ([NGAYTHI], [TIETBATDAU], [MAPHONG], [MANV]) VALUES (CAST(0x233F0B00 AS Date), 1, N'2A16      ', N'GV02      ')
INSERT [dbo].[CBCOITHI] ([NGAYTHI], [TIETBATDAU], [MAPHONG], [MANV]) VALUES (CAST(0x7D3F0B00 AS Date), 1, N'2A08      ', N'GV02      ')
INSERT [dbo].[CBCOITHI] ([NGAYTHI], [TIETBATDAU], [MAPHONG], [MANV]) VALUES (CAST(0x34400B00 AS Date), 1, N'2B13      ', N'GV02      ')
INSERT [dbo].[CBCOITHI] ([NGAYTHI], [TIETBATDAU], [MAPHONG], [MANV]) VALUES (CAST(0x233F0B00 AS Date), 1, N'2A16      ', N'GV04      ')
INSERT [dbo].[CBCOITHI] ([NGAYTHI], [TIETBATDAU], [MAPHONG], [MANV]) VALUES (CAST(0x7D3F0B00 AS Date), 1, N'2A08      ', N'GV04      ')
/****** Object:  View [dbo].[V_LICHTHI]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[V_LICHTHI]
AS
SELECT     dbo.MON.TENMON, dbo.CTLICHTHI.MAPHONG, dbo.CTLICHTHI.NGAYTHI, dbo.CTLICHTHI.TIETBATDAU, dbo.LOPTC.MALOPTC, dbo.LOPTC.NHOM, dbo.LOPTC.NIENKHOA, dbo.LOPTC.HOCKY
FROM         dbo.LOPTC INNER JOIN
                      dbo.CTLICHTHI ON dbo.LOPTC.MALOPTC = dbo.CTLICHTHI.MALOPTC INNER JOIN
                      dbo.MON ON dbo.LOPTC.MAMON = dbo.MON.MAMON
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "LOPTC"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 126
               Right = 198
            End
            DisplayFlags = 280
            TopColumn = 3
         End
         Begin Table = "CTLICHTHI"
            Begin Extent = 
               Top = 6
               Left = 236
               Bottom = 126
               Right = 396
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "MON"
            Begin Extent = 
               Top = 6
               Left = 632
               Bottom = 96
               Right = 792
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'V_LICHTHI'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'V_LICHTHI'
GO
/****** Object:  StoredProcedure [dbo].[SP_LICHTHI2]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[SP_LICHTHI2]


as
begin
	SELECT LT.NGAYTHI,LT.MAPHONG,LT.TIETBATDAU,LT.MALOPTC , lt.DOTTHI
	INTO #LT
	FROM CTLICHTHI LT
	
	SELECT *
	INTO #LOPTCTAM
	FROM LOPTC 
	
	SELECT TC.MALOPTC,TC.MALOP,MON.MAMON,MON.TENMON,NV.HO + ' ' + NV.TEN AS 'HOTEN',TC.NHOM,TC.SLDANGKY,#LT.NGAYTHI,#LT.MAPHONG,#LT.TIETBATDAU,#lt.DOTTHI,TC.NIENKHOA,tC.HOCKY
	FROM #LOPTCTAM TC,#LT,NHANVIEN NV,LOP,MON
	WHERE
		TC.MALOPTC = #LT.MALOPTC AND NV.MANV = TC.MANV AND LOP.MALOP = TC.MALOP AND MON.MAMON = TC.MAMON
		order by #LT.NGAYTHI,#LT.TIETBATDAU

end
GO
/****** Object:  StoredProcedure [dbo].[SP_LICHTHI]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[SP_LICHTHI]
@dot INT,@hocky int,@nienkhoa nvarchar(12)

as
begin
	SELECT LT.NGAYTHI,LT.MAPHONG,LT.TIETBATDAU,LT.MALOPTC,LT.DOTTHI
	INTO #LT
	FROM CTLICHTHI LT
	WHERE @dot = LT.DOTTHI 
	SELECT *
	INTO #LOPTCTAM
	FROM LOPTC 
	WHERE @nienkhoa = LOPTC.NIENKHOA AND @hocky = LOPTC.HOCKY 
	SELECT TC.MALOPTC,TC.MALOP,MON.MAMON,MON.TENMON,NV.HO + ' ' + NV.TEN AS 'HOTEN',TC.NHOM,TC.SLDANGKY,#LT.NGAYTHI,#LT.MAPHONG,#LT.TIETBATDAU,#LT.DOTTHI
	FROM #LOPTCTAM TC,#LT,NHANVIEN NV,LOP,MON
	WHERE
		TC.MALOPTC = #LT.MALOPTC AND NV.MANV = TC.MANV AND LOP.MALOP = TC.MALOP AND MON.MAMON = TC.MAMON

end
GO
/****** Object:  StoredProcedure [dbo].[SP_GVRADE]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[SP_GVRADE]
AS
SELECT LOPTC.MALOPTC, MAMON, NV = ISNULL(DETHI.MANV, ''), SLBT = ISNULL(SOLUONGDE, 0)INTO #TC FROM LOPTC LEFT JOIN DETHI ON LOPTC.MALOPTC = DETHI.MALOPTC 
SELECT #TC.MALOPTC, MON.TENMON, NV, SLBT INTO #LTCMON FROM #TC, MON WHERE #TC.MAMON = MON.MAMON
SELECT #LTCMON.MALOPTC, #LTCMON.TENMON, MANV = ISNULL(MANV, ''), 'GV_RADETHI' = ISNULL(HO + ' ' + TEN, '') , SLBT AS'SOLUONGBAITHI'
FROM
 #LTCMON  LEFT JOIN  NHANVIEN
 ON NHANVIEN.MANV = #LTCMON.NV
GO
/****** Object:  StoredProcedure [dbo].[SP_GVCT]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[SP_GVCT]
AS
SELECT LOPTC.MALOPTC, MAMON, NV = ISNULL(BAITHI.MANV, ''), SLBT = ISNULL(SOLUONGBAITHI, 0)INTO #TC FROM LOPTC LEFT JOIN BAITHI ON LOPTC.MALOPTC = BAITHI.MALOPTC 

SELECT #TC.MALOPTC, MON.TENMON, NV, SLBT INTO #LTCMON FROM #TC, MON WHERE #TC.MAMON = MON.MAMON
SELECT #LTCMON.MALOPTC, #LTCMON.TENMON, MANV = ISNULL(MANV, ''), 'GV_CHAMTHI' = ISNULL(HO + ' ' + TEN, '') , SLBT AS'SOLUONGBAITHI'
FROM #LTCMON  LEFT JOIN  NHANVIEN
 ON NHANVIEN.MANV = #LTCMON.NV
 
--
GO
/****** Object:  StoredProcedure [dbo].[SP_DELETENV]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[SP_DELETENV]
@MANV NVARCHAR (20)
AS
delete from CBCOITHI where MANV = @MANV 
delete from BAITHI where MANV = @MANV 
delete from DETHI where MANV = @MANV
UPDATE LOPTC SET MANV = NULL WHERE MANV = @MANV
delete from NHANVIEN where MANV = @MANV 
--
GO
/****** Object:  StoredProcedure [dbo].[SP_KHOILUONG2]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_KHOILUONG2]

AS
	BEGIN
		SELECT TC.HOCKY,TC.MALOP,TC.MALOPTC,TC.MAMON,TC.MANV AS 'GVDAY',TC.NHOM,TC.NIENKHOA,TC.SLDANGKY
		INTO #TC--Loc lop tc
		FROM LOPTC TC
		
		
		SELECT Mon.TENMON,#TC.MAMON
		INTO #TCMON
		FROM #TC,MON--KET 2 BANG TC&MON
		WHERE #TC.MAMON = Mon.MAMON
		
		SELECT #TC.MALOPTC,#TC.MAMON,CTLICHTHI.MAPHONG,CTLICHTHI.NGAYTHI,CTLICHTHI.TIETBATDAU,CTLICHTHI.SLDUTHI
		INTO #TCLICHTHI
		FROM #TC,CTLICHTHI--KET 2 BANG TC&CTLICHTHI
		WHERE #TC.MALOPTC = CTLICHTHI.MALOPTC
		
		SELECT DISTINCT #TCLICHTHI.*,MIN(CBCOITHI.MANV )as 'MANVCT1' 
		INTO #TCLICHTHICB
		FROM  CBCOITHI inner join #TCLICHTHI  --LỌC CBCOITHI ĐẦU TIÊN
		on #TCLICHTHI.NGAYTHI = CBCOITHI.NGAYTHI AND #TCLICHTHI.MAPHONG = CBCOITHI.MAPHONG AND #TCLICHTHI.TIETBATDAU = CBCOITHI.TIETBATDAU
		GROUP BY #TCLICHTHI.MALOPTC,#TCLICHTHI.MAMON,#TCLICHTHI.MAPHONG,#TCLICHTHI.NGAYTHI,#TCLICHTHI.SLDUTHI,#TCLICHTHI.TIETBATDAU
		
		--DROP TABLE #TCLICHTHI
		
		SELECT DISTINCT #TCLICHTHICB.*,COUNT(CBCOITHI.MANV )as 'SOLANCT1' 
		INTO #TCLICHTHICBTEMP
		FROM #TCLICHTHICB  , CBCOITHI--ĐẾM SỐ LẦN COI THI CỦA CB1
		WHERE #TCLICHTHICB.NGAYTHI = CBCOITHI.NGAYTHI AND #TCLICHTHICB.MAPHONG = CBCOITHI.MAPHONG AND #TCLICHTHICB.TIETBATDAU = CBCOITHI.TIETBATDAU AND #TCLICHTHICB.MANVCT1 = CBCOITHI.MANV
		GROUP BY #TCLICHTHICB.MALOPTC,#TCLICHTHICB.MAMON,#TCLICHTHICB.MAPHONG,#TCLICHTHICB.NGAYTHI,#TCLICHTHICB.SLDUTHI,#TCLICHTHICB.TIETBATDAU,#TCLICHTHICB.MANVCT1
		
		--DROP TABLE #TCLICHTHICB
		
		SELECT DISTINCT #TCLICHTHICBTEMP.*,Min(CBCOITHI.MANV) AS 'MANVCT2' 
		INTO #TCLICHTHICBTEMP3
		FROM #TCLICHTHICBTEMP  inner join CBCOITHI--LỌC CBCT VÀ SỐ LẦN COI THI CỦA CB2(NẾU CÓ)
		ON #TCLICHTHICBTEMP.NGAYTHI = CBCOITHI.NGAYTHI AND #TCLICHTHICBTEMP.MAPHONG = CBCOITHI.MAPHONG AND #TCLICHTHICBTEMP.TIETBATDAU = CBCOITHI.TIETBATDAU AND #TCLICHTHICBTEMP.MANVCT1 != CBCOITHI.MANV 
		GROUP BY #TCLICHTHICBTEMP.MALOPTC,#TCLICHTHICBTEMP.MAMON,#TCLICHTHICBTEMP.MAPHONG,#TCLICHTHICBTEMP.NGAYTHI,#TCLICHTHICBTEMP.SLDUTHI,#TCLICHTHICBTEMP.TIETBATDAU,#TCLICHTHICBTEMP.MANVCT1,#TCLICHTHICBTEMP.SOLANCT1
		SELECT DISTINCT #TCLICHTHICBTEMP3.*,ISNULL(Count(CBCOITHI.MANV),'') AS 'SOLANCT2' 
		INTO #TCLICHTHICBTEMP2
		FROM #TCLICHTHICBTEMP3  , CBCOITHI--LỌC CBCT VÀ SỐ LẦN COI THI CỦA CB2(NẾU CÓ)
		where #TCLICHTHICBTEMP3.NGAYTHI = CBCOITHI.NGAYTHI AND #TCLICHTHICBTEMP3.MAPHONG = CBCOITHI.MAPHONG AND #TCLICHTHICBTEMP3.TIETBATDAU = CBCOITHI.TIETBATDAU AND #TCLICHTHICBTEMP3.MANVCT1 != CBCOITHI.MANV 
		GROUP BY #TCLICHTHICBTEMP3.MALOPTC,#TCLICHTHICBTEMP3.MAMON,#TCLICHTHICBTEMP3.MAPHONG,#TCLICHTHICBTEMP3.NGAYTHI,#TCLICHTHICBTEMP3.SLDUTHI,#TCLICHTHICBTEMP3.TIETBATDAU,CBCOITHI.MANV,#TCLICHTHICBTEMP3.MANVCT1,#TCLICHTHICBTEMP3.SOLANCT1,#TCLICHTHICBTEMP3.MANVCT2
		
		--DROP TABLE #TCLICHTHICBTEMP
		
		SELECT DISTINCT #TCLICHTHICBTEMP2.*,ISNULL(CBCOITHI.MANV,'') AS 'MANVCT3' ,ISNULL(COUNT(CBCOITHI.MANV),' ') AS 'SOLANCT3'
		INTO #TCLICHTHICB2
		FROM #TCLICHTHICBTEMP2  LEFT JOIN CBCOITHI----LỌC CBCT VÀ SỐ LẦN COI THI CỦA CB3(NẾU CÓ)
		ON #TCLICHTHICBTEMP2.NGAYTHI = CBCOITHI.NGAYTHI AND #TCLICHTHICBTEMP2.MAPHONG = CBCOITHI.MAPHONG AND #TCLICHTHICBTEMP2.TIETBATDAU = CBCOITHI.TIETBATDAU AND #TCLICHTHICBTEMP2.MANVCT1 != CBCOITHI.MANV AND #TCLICHTHICBTEMP2.MANVCT2 != CBCOITHI.MANV
		GROUP BY #TCLICHTHICBTEMP2.MALOPTC,#TCLICHTHICBTEMP2.MAMON,#TCLICHTHICBTEMP2.MAPHONG,#TCLICHTHICBTEMP2.NGAYTHI,#TCLICHTHICBTEMP2.SLDUTHI,#TCLICHTHICBTEMP2.TIETBATDAU,CBCOITHI.MANV,#TCLICHTHICBTEMP2.MANVCT1,#TCLICHTHICBTEMP2.SOLANCT1,#TCLICHTHICBTEMP2.MANVCT2,#TCLICHTHICBTEMP2.SOLANCT2
		
		--DROP TABLE #TCLICHTHICBTEMP2
		
		SELECT #TC.*,ISNULL(DETHI.MANV,'') AS 'GVRADE',ISNULL(DETHI.SOLUONGDE,'') AS 'SOLUONGDE'
		INTO #TCDETHI
		FROM #TC LEFT JOIN DETHI 
		ON #TC.MALOPTC = DETHI.MALOPTC--Lọc mã gv ra đề
		
		--DROP TABLE #TC
		
		SELECT #TCDETHI.*,ISNULL(BAITHI.MANV,'') AS 'GVCHAMTHI',ISNULL(BAITHI.SOLUONGBAITHI,'') AS 'SOLUONGBAITHI'
		INTO #TCCHUNG
		FROM #TCDETHI LEFT JOIN BAITHI -- lọc mã nv chấm thi
		ON #TCDETHI.MALOPTC = BAITHI.MALOPTC
		
		--DROP TABLE #TCDETHI
		
		SELECT TOBOMON.MATBM,PBKHOA.TENPBK
		INTO #PBK
		FROM PBKHOA,TOBOMON -- Kết bảng Tổ BM với Khoa để lấy tên PBkhoa
		WHERE PBKHOA.MAPBK = TOBOMON.MAPBK
		
		SELECT NV.MANV,NV.HO + ' ' + NV.TEN AS 'HOTEN' ,#PBK.TENPBK
		INTO #NVANDKHOA
		FROM #PBK, NHANVIEN NV-- Kết nhân viên với tên PBK
		WHERE #PBK.MATBM = NV.MATBM
		
		--DROP TABLE #PBK
		
		SELECT #TCCHUNG.*,#NVANDKHOA.HOTEN AS 'HOTENGVDAY',#NVANDKHOA.TENPBK AS 'KHOA'
		INTO #TCCHUNG2
		FROM #TCCHUNG,#NVANDKHOA--Kết họ tên GV Dạy và tên khoa với bảng Lớp TC
		WHERE #TCCHUNG.GVDAY = #NVANDKHOA.MANV
		
		--DROP TABLE #TCCHUNG
		
		SELECT #TCCHUNG2.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENGVRADE'
		INTO #TCCHUNG3
		FROM #TCCHUNG2 LEFT JOIN #NVANDKHOA--Kết họ tên GV ra đề với bảng Lớp TC
		ON #TCCHUNG2.GVRADE = #NVANDKHOA.MANV
		
		--DROP TABLE #TCCHUNG2
		
		SELECT #TCCHUNG3.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENGVCHAMTHI'
		INTO #TCCHUNG4
		FROM #TCCHUNG3 LEFT JOIN #NVANDKHOA----Kết họ tên GV chấm thi với bảng Lớp TC
		ON #TCCHUNG3.GVCHAMTHI = #NVANDKHOA.MANV
		
		--DROP TABLE #TCCHUNG3
		
		SELECT #TCLICHTHICB2.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENCBCT1'
		INTO #CBCT1
		FROM #TCLICHTHICB2 LEFT JOIN #NVANDKHOA --Kết họ tên CBCOITHI1 với bảng cbcoithi
		ON #TCLICHTHICB2.MANVCT1 = #NVANDKHOA.MANV
		
		--DROP TABLE #TCLICHTHICB2
		
		SELECT #CBCT1.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENCBCT2'
		INTO #CBCT2
		FROM #CBCT1 LEFT JOIN #NVANDKHOA--Kết họ tên CBCOITHI2 với bảng cbcoithi
		ON #CBCT1.MANVCT2 = #NVANDKHOA.MANV
		
		--DROP TABLE #CBCT1
		
		SELECT #CBCT2.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENCBCT3'
		INTO #CBCT3
		FROM #CBCT2 LEFT JOIN #NVANDKHOA--Kết họ tên CBCOITHI3 với bảng cbcoithi
		ON #CBCT2.MANVCT3 = #NVANDKHOA.MANV
		
		--DROP TABLE #CBCT2
		--DROP TABLE #NVANDKHOA
		
		SELECT MH.TENMON,TC.MALOPTC,TC.NIENKHOA,TC.HOCKY,TC.NHOM,TC.HOTENGVDAY AS 'GVGIANGDAY',TC.KHOA,TC.HOTENGVRADE AS 'GVRADE',TC.HOTENGVCHAMTHI AS 'GVCHAMTHI',CB.MAPHONG AS 'PHONGTHI',CB.TIETBATDAU,CB.NGAYTHI,CB.SLDUTHI,CB.HOTENCBCT1 AS 'CBCT1',CB.SOLANCT1,CB.HOTENCBCT2 AS 'CBCT2',CB.SOLANCT2,CB.HOTENCBCT3 AS 'CBCT3',CB.SOLANCT3,TC.SOLUONGBAITHI,TC.SOLUONGDE
		FROM #TCCHUNG4 TC ,#TCMON MH,#CBCT3 CB
		WHERE TC.MALOPTC = CB.MALOPTC AND TC.MAMON = MH.MAMON 
		ORDER BY CB.NGAYTHI,MH.TENMON
		
		--DROP TABLE #CBCT3
		--DROP TABLE #TCCHUNG4
		--DROP TABLE #TCMON

	END
GO
/****** Object:  StoredProcedure [dbo].[SP_KHOILUONG]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_KHOILUONG]
@hocky int,@nienkhoa int
AS
	BEGIN
		SELECT TC.HOCKY,TC.MALOP,TC.MALOPTC,TC.MAMON,TC.MANV AS 'GVDAY',TC.NHOM,TC.NIENKHOA,TC.SLDANGKY
		INTO #TC--Loc lop tc
		FROM LOPTC TC
		WHERE @nienkhoa = TC.NIENKHOA AND @hocky = TC.HOCKY
		
		SELECT Mon.TENMON,#TC.MAMON
		INTO #TCMON
		FROM #TC,MON--KET 2 BANG TC&MON
		WHERE #TC.MAMON = Mon.MAMON
		
		SELECT #TC.MALOPTC,#TC.MAMON,CTLICHTHI.MAPHONG,CTLICHTHI.NGAYTHI,CTLICHTHI.TIETBATDAU,CTLICHTHI.SLDUTHI
		INTO #TCLICHTHI
		FROM #TC,CTLICHTHI--KET 2 BANG TC&CTLICHTHI
		WHERE #TC.MALOPTC = CTLICHTHI.MALOPTC
		
		SELECT DISTINCT #TCLICHTHI.*,MIN(CBCOITHI.MANV )as 'MANVCT1' 
		INTO #TCLICHTHICB
		FROM #TCLICHTHI  , CBCOITHI--LỌC CBCOITHI ĐẦU TIÊN
		WHERE #TCLICHTHI.NGAYTHI = CBCOITHI.NGAYTHI AND #TCLICHTHI.MAPHONG = CBCOITHI.MAPHONG AND #TCLICHTHI.TIETBATDAU = CBCOITHI.TIETBATDAU
		GROUP BY #TCLICHTHI.MALOPTC,#TCLICHTHI.MAMON,#TCLICHTHI.MAPHONG,#TCLICHTHI.NGAYTHI,#TCLICHTHI.SLDUTHI,#TCLICHTHI.TIETBATDAU
		
		DROP TABLE #TCLICHTHI
		
		SELECT DISTINCT #TCLICHTHICB.*,COUNT(CBCOITHI.MANV )as 'SOLANCT1' 
		INTO #TCLICHTHICBTEMP
		FROM #TCLICHTHICB  , CBCOITHI--ĐẾM SỐ LẦN COI THI CỦA CB1
		WHERE #TCLICHTHICB.NGAYTHI = CBCOITHI.NGAYTHI AND #TCLICHTHICB.MAPHONG = CBCOITHI.MAPHONG AND #TCLICHTHICB.TIETBATDAU = CBCOITHI.TIETBATDAU AND #TCLICHTHICB.MANVCT1 = CBCOITHI.MANV
		GROUP BY #TCLICHTHICB.MALOPTC,#TCLICHTHICB.MAMON,#TCLICHTHICB.MAPHONG,#TCLICHTHICB.NGAYTHI,#TCLICHTHICB.SLDUTHI,#TCLICHTHICB.TIETBATDAU,#TCLICHTHICB.MANVCT1
		
		DROP TABLE #TCLICHTHICB
		
		SELECT DISTINCT #TCLICHTHICBTEMP.*,ISNULL(CBCOITHI.MANV,'') AS 'MANVCT2' ,ISNULL(COUNT(CBCOITHI.MANV),' ') AS 'SOLANCT2'
		INTO #TCLICHTHICBTEMP2
		FROM #TCLICHTHICBTEMP  LEFT JOIN CBCOITHI--LỌC CBCT VÀ SỐ LẦN COI THI CỦA CB2(NẾU CÓ)
		ON #TCLICHTHICBTEMP.NGAYTHI = CBCOITHI.NGAYTHI AND #TCLICHTHICBTEMP.MAPHONG = CBCOITHI.MAPHONG AND #TCLICHTHICBTEMP.TIETBATDAU = CBCOITHI.TIETBATDAU AND #TCLICHTHICBTEMP.MANVCT1 != CBCOITHI.MANV 
		GROUP BY #TCLICHTHICBTEMP.MALOPTC,#TCLICHTHICBTEMP.MAMON,#TCLICHTHICBTEMP.MAPHONG,#TCLICHTHICBTEMP.NGAYTHI,#TCLICHTHICBTEMP.SLDUTHI,#TCLICHTHICBTEMP.TIETBATDAU,CBCOITHI.MANV,#TCLICHTHICBTEMP.MANVCT1,#TCLICHTHICBTEMP.SOLANCT1
		
		DROP TABLE #TCLICHTHICBTEMP
		
		SELECT DISTINCT #TCLICHTHICBTEMP2.*,ISNULL(CBCOITHI.MANV,'') AS 'MANVCT3' ,ISNULL(COUNT(CBCOITHI.MANV),' ') AS 'SOLANCT3'
		INTO #TCLICHTHICB2
		FROM #TCLICHTHICBTEMP2  LEFT JOIN CBCOITHI----LỌC CBCT VÀ SỐ LẦN COI THI CỦA CB3(NẾU CÓ)
		ON #TCLICHTHICBTEMP2.NGAYTHI = CBCOITHI.NGAYTHI AND #TCLICHTHICBTEMP2.MAPHONG = CBCOITHI.MAPHONG AND #TCLICHTHICBTEMP2.TIETBATDAU = CBCOITHI.TIETBATDAU AND #TCLICHTHICBTEMP2.MANVCT1 != CBCOITHI.MANV AND #TCLICHTHICBTEMP2.MANVCT2 != CBCOITHI.MANV
		GROUP BY #TCLICHTHICBTEMP2.MALOPTC,#TCLICHTHICBTEMP2.MAMON,#TCLICHTHICBTEMP2.MAPHONG,#TCLICHTHICBTEMP2.NGAYTHI,#TCLICHTHICBTEMP2.SLDUTHI,#TCLICHTHICBTEMP2.TIETBATDAU,CBCOITHI.MANV,#TCLICHTHICBTEMP2.MANVCT1,#TCLICHTHICBTEMP2.SOLANCT1,#TCLICHTHICBTEMP2.MANVCT2,#TCLICHTHICBTEMP2.SOLANCT2
		
		DROP TABLE #TCLICHTHICBTEMP2
		
		SELECT #TC.*,ISNULL(DETHI.MANV,'') AS 'GVRADE',ISNULL(DETHI.SOLUONGDE,'') AS 'SOLUONGDE'
		INTO #TCDETHI
		FROM #TC LEFT JOIN DETHI 
		ON #TC.MALOPTC = DETHI.MALOPTC--Lọc mã gv ra đề
		
		DROP TABLE #TC
		
		SELECT #TCDETHI.*,ISNULL(BAITHI.MANV,'') AS 'GVCHAMTHI',ISNULL(BAITHI.SOLUONGBAITHI,'') AS 'SOLUONGBAITHI'
		INTO #TCCHUNG
		FROM #TCDETHI LEFT JOIN BAITHI -- lọc mã nv chấm thi
		ON #TCDETHI.MALOPTC = BAITHI.MALOPTC
		
		DROP TABLE #TCDETHI
		
		SELECT TOBOMON.MATBM,PBKHOA.TENPBK
		INTO #PBK
		FROM PBKHOA,TOBOMON -- Kết bảng Tổ BM với Khoa để lấy tên PBkhoa
		WHERE PBKHOA.MAPBK = TOBOMON.MAPBK
		
		SELECT NV.MANV,NV.HO + ' ' + NV.TEN AS 'HOTEN' ,#PBK.TENPBK
		INTO #NVANDKHOA
		FROM #PBK, NHANVIEN NV-- Kết nhân viên với tên PBK
		WHERE #PBK.MATBM = NV.MATBM
		
		DROP TABLE #PBK
		
		SELECT #TCCHUNG.*,#NVANDKHOA.HOTEN AS 'HOTENGVDAY',#NVANDKHOA.TENPBK AS 'KHOA'
		INTO #TCCHUNG2
		FROM #TCCHUNG,#NVANDKHOA--Kết họ tên GV Dạy và tên khoa với bảng Lớp TC
		WHERE #TCCHUNG.GVDAY = #NVANDKHOA.MANV
		
		DROP TABLE #TCCHUNG
		
		SELECT #TCCHUNG2.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENGVRADE'
		INTO #TCCHUNG3
		FROM #TCCHUNG2 LEFT JOIN #NVANDKHOA--Kết họ tên GV ra đề với bảng Lớp TC
		ON #TCCHUNG2.GVRADE = #NVANDKHOA.MANV
		
		DROP TABLE #TCCHUNG2
		
		SELECT #TCCHUNG3.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENGVCHAMTHI'
		INTO #TCCHUNG4
		FROM #TCCHUNG3 LEFT JOIN #NVANDKHOA----Kết họ tên GV chấm thi với bảng Lớp TC
		ON #TCCHUNG3.GVCHAMTHI = #NVANDKHOA.MANV
		
		DROP TABLE #TCCHUNG3
		
		SELECT #TCLICHTHICB2.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENCBCT1'
		INTO #CBCT1
		FROM #TCLICHTHICB2 LEFT JOIN #NVANDKHOA --Kết họ tên CBCOITHI1 với bảng cbcoithi
		ON #TCLICHTHICB2.MANVCT1 = #NVANDKHOA.MANV
		
		DROP TABLE #TCLICHTHICB2
		
		SELECT #CBCT1.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENCBCT2'
		INTO #CBCT2
		FROM #CBCT1 LEFT JOIN #NVANDKHOA--Kết họ tên CBCOITHI2 với bảng cbcoithi
		ON #CBCT1.MANVCT2 = #NVANDKHOA.MANV
		
		DROP TABLE #CBCT1
		
		SELECT #CBCT2.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENCBCT3'
		INTO #CBCT3
		FROM #CBCT2 LEFT JOIN #NVANDKHOA--Kết họ tên CBCOITHI3 với bảng cbcoithi
		ON #CBCT2.MANVCT3 = #NVANDKHOA.MANV
		
		DROP TABLE #CBCT2
		DROP TABLE #NVANDKHOA
		
		SELECT MH.TENMON,TC.NHOM,TC.HOTENGVDAY AS 'GVGIANGDAY',TC.KHOA,TC.HOTENGVRADE AS 'GVRADE',TC.HOTENGVCHAMTHI AS 'GVCHAMTHI',CB.MAPHONG AS 'PHONGTHI',CB.TIETBATDAU,CB.NGAYTHI,CB.SLDUTHI,CB.HOTENCBCT1 AS 'CBCT1',CB.SOLANCT1,CB.HOTENCBCT2 AS 'CBCT2',CB.SOLANCT2,CB.HOTENCBCT3 AS 'CBCT3',CB.SOLANCT3,TC.SOLUONGBAITHI,TC.SOLUONGDE
		FROM #TCCHUNG4 TC ,#TCMON MH,#CBCT3 CB
		WHERE TC.MALOPTC = CB.MALOPTC AND TC.MAMON = MH.MAMON 
		ORDER BY CB.NGAYTHI,MH.TENMON
		
		DROP TABLE #CBCT3
		DROP TABLE #TCCHUNG4
		DROP TABLE #TCMON

	END
GO
/****** Object:  StoredProcedure [dbo].[SP_THANHTOAN]    Script Date: 11/21/2019 20:58:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THANHTOAN]
@MAGV NVARCHAR(12),@HOCKY INT,@NIENKHOA NVARCHAR(12)
AS
BEGIN
		SELECT TC.HOCKY,TC.MALOP,TC.MALOPTC,TC.MAMON,TC.MANV AS 'GVDAY',TC.NHOM,TC.NIENKHOA,TC.SLDANGKY
		INTO #TC--Loc lop tc
		FROM LOPTC TC
		WHERE @MAGV = TC.MANV AND @NIENKHOA = TC.NIENKHOA AND @hocky = TC.HOCKY
		
		SELECT Mon.TENMON,#TC.MAMON
		INTO #TCMON
		FROM #TC,MON--KET 2 BANG TC&MON
		WHERE #TC.MAMON = Mon.MAMON
		
		SELECT CTDONGIA.*
		INTO #DG
		FROM #TCMON LEFT JOIN CTDONGIA 
		ON #TCMON.MAMON = CTDONGIA.MAMON
		SELECT #TC.MALOPTC,#TC.MAMON,CTLICHTHI.MAPHONG,CTLICHTHI.NGAYTHI,CTLICHTHI.TIETBATDAU,CTLICHTHI.SLDUTHI
		INTO #TCLICHTHI
		FROM #TC LEFT JOIN CTLICHTHI--KET 2 BANG TC&CTLICHTHI
		ON #TC.MALOPTC = CTLICHTHI.MALOPTC
		
		SELECT DISTINCT #TCLICHTHI.*,MIN(CBCOITHI.MANV )as 'MANVCT1' 
		INTO #TCLICHTHICB
		FROM #TCLICHTHI  LEFT JOIN CBCOITHI--LỌC CBCOITHI ĐẦU TIÊN
		ON #TCLICHTHI.NGAYTHI = CBCOITHI.NGAYTHI AND #TCLICHTHI.MAPHONG = CBCOITHI.MAPHONG AND #TCLICHTHI.TIETBATDAU = CBCOITHI.TIETBATDAU
		GROUP BY #TCLICHTHI.MALOPTC,#TCLICHTHI.MAMON,#TCLICHTHI.MAPHONG,#TCLICHTHI.NGAYTHI,#TCLICHTHI.SLDUTHI,#TCLICHTHI.TIETBATDAU
		
		DROP TABLE #TCLICHTHI
		
		SELECT DISTINCT #TCLICHTHICB.*,COUNT(CBCOITHI.MANV )as 'SOLANCT1' 
		INTO #TCLICHTHICBTEMP
		FROM #TCLICHTHICB  LEFT JOIN CBCOITHI--ĐẾM SỐ LẦN COI THI CỦA CB1
		ON #TCLICHTHICB.NGAYTHI = CBCOITHI.NGAYTHI AND #TCLICHTHICB.MAPHONG = CBCOITHI.MAPHONG AND #TCLICHTHICB.TIETBATDAU = CBCOITHI.TIETBATDAU AND #TCLICHTHICB.MANVCT1 = CBCOITHI.MANV
		GROUP BY #TCLICHTHICB.MALOPTC,#TCLICHTHICB.MAMON,#TCLICHTHICB.MAPHONG,#TCLICHTHICB.NGAYTHI,#TCLICHTHICB.SLDUTHI,#TCLICHTHICB.TIETBATDAU,#TCLICHTHICB.MANVCT1
		
		DROP TABLE #TCLICHTHICB
		
		SELECT DISTINCT #TCLICHTHICBTEMP.*,ISNULL(CBCOITHI.MANV,'') AS 'MANVCT2' ,ISNULL(COUNT(CBCOITHI.MANV),' ') AS 'SOLANCT2'
		INTO #TCLICHTHICBTEMP2
		FROM #TCLICHTHICBTEMP  LEFT JOIN CBCOITHI--LỌC CBCT VÀ SỐ LẦN COI THI CỦA CB2(NẾU CÓ)
		ON #TCLICHTHICBTEMP.NGAYTHI = CBCOITHI.NGAYTHI AND #TCLICHTHICBTEMP.MAPHONG = CBCOITHI.MAPHONG AND #TCLICHTHICBTEMP.TIETBATDAU = CBCOITHI.TIETBATDAU AND #TCLICHTHICBTEMP.MANVCT1 != CBCOITHI.MANV 
		GROUP BY #TCLICHTHICBTEMP.MALOPTC,#TCLICHTHICBTEMP.MAMON,#TCLICHTHICBTEMP.MAPHONG,#TCLICHTHICBTEMP.NGAYTHI,#TCLICHTHICBTEMP.SLDUTHI,#TCLICHTHICBTEMP.TIETBATDAU,CBCOITHI.MANV,#TCLICHTHICBTEMP.MANVCT1,#TCLICHTHICBTEMP.SOLANCT1
		
		DROP TABLE #TCLICHTHICBTEMP
		
		SELECT DISTINCT #TCLICHTHICBTEMP2.*,ISNULL(CBCOITHI.MANV,'') AS 'MANVCT3' ,ISNULL(COUNT(CBCOITHI.MANV),' ') AS 'SOLANCT3'
		INTO #TCLICHTHICB2
		FROM #TCLICHTHICBTEMP2  LEFT JOIN CBCOITHI----LỌC CBCT VÀ SỐ LẦN COI THI CỦA CB3(NẾU CÓ)
		ON #TCLICHTHICBTEMP2.NGAYTHI = CBCOITHI.NGAYTHI AND #TCLICHTHICBTEMP2.MAPHONG = CBCOITHI.MAPHONG AND #TCLICHTHICBTEMP2.TIETBATDAU = CBCOITHI.TIETBATDAU AND #TCLICHTHICBTEMP2.MANVCT1 != CBCOITHI.MANV AND #TCLICHTHICBTEMP2.MANVCT2 != CBCOITHI.MANV
		GROUP BY #TCLICHTHICBTEMP2.MALOPTC,#TCLICHTHICBTEMP2.MAMON,#TCLICHTHICBTEMP2.MAPHONG,#TCLICHTHICBTEMP2.NGAYTHI,#TCLICHTHICBTEMP2.SLDUTHI,#TCLICHTHICBTEMP2.TIETBATDAU,CBCOITHI.MANV,#TCLICHTHICBTEMP2.MANVCT1,#TCLICHTHICBTEMP2.SOLANCT1,#TCLICHTHICBTEMP2.MANVCT2,#TCLICHTHICBTEMP2.SOLANCT2
		
		DROP TABLE #TCLICHTHICBTEMP2
		
		SELECT #TC.*,ISNULL(DETHI.MANV,'') AS 'GVRADE',ISNULL(DETHI.SOLUONGDE,'') AS 'SOLUONGDE',DETHI.NGAY AS 'NGAYRADE'
		INTO #TCDETHI
		FROM #TC LEFT JOIN DETHI 
		ON #TC.MALOPTC = DETHI.MALOPTC--Lọc mã gv ra đề
		
		DROP TABLE #TC
		
		SELECT #TCDETHI.*,ISNULL(BAITHI.MANV,'') AS 'GVCHAMTHI',ISNULL(BAITHI.SOLUONGBAITHI,'') AS 'SOLUONGBAITHI',BAITHI.NGAY AS 'NGAYCHAM'
		INTO #TCCHUNG
		FROM #TCDETHI LEFT JOIN BAITHI -- lọc mã nv chấm thi
		ON #TCDETHI.MALOPTC = BAITHI.MALOPTC
		
		DROP TABLE #TCDETHI
		
		SELECT TOBOMON.MATBM,PBKHOA.TENPBK
		INTO #PBK
		FROM PBKHOA,TOBOMON -- Kết bảng Tổ BM với Khoa để lấy tên PBkhoa
		WHERE PBKHOA.MAPBK = TOBOMON.MAPBK
		
		SELECT NV.MANV,NV.HO + ' ' + NV.TEN AS 'HOTEN' ,#PBK.TENPBK
		INTO #NVANDKHOA
		FROM #PBK, NHANVIEN NV-- Kết nhân viên với tên PBK
		WHERE #PBK.MATBM = NV.MATBM
		
		DROP TABLE #PBK
		
		SELECT #TCCHUNG.*,#NVANDKHOA.HOTEN AS 'HOTENGVDAY',#NVANDKHOA.TENPBK AS 'KHOA'
		INTO #TCCHUNG2
		FROM #TCCHUNG LEFT JOIN #NVANDKHOA--Kết họ tên GV Dạy và tên khoa với bảng Lớp TC
		ON #TCCHUNG.GVDAY = #NVANDKHOA.MANV
		
		DROP TABLE #TCCHUNG
		
		SELECT #TCCHUNG2.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENGVRADE'
		INTO #TCCHUNG3
		FROM #TCCHUNG2 LEFT JOIN #NVANDKHOA--Kết họ tên GV ra đề với bảng Lớp TC
		ON #TCCHUNG2.GVRADE = #NVANDKHOA.MANV
		
		DROP TABLE #TCCHUNG2
		
		SELECT #TCCHUNG3.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENGVCHAMTHI'
		INTO #TCCHUNG4
		FROM #TCCHUNG3 LEFT JOIN #NVANDKHOA----Kết họ tên GV chấm thi với bảng Lớp TC
		ON #TCCHUNG3.GVCHAMTHI = #NVANDKHOA.MANV
		
		DROP TABLE #TCCHUNG3
		
		SELECT #TCLICHTHICB2.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENCBCT1'
		INTO #CBCT1
		FROM #TCLICHTHICB2 LEFT JOIN #NVANDKHOA --Kết họ tên CBCOITHI1 với bảng cbcoithi
		ON #TCLICHTHICB2.MANVCT1 = #NVANDKHOA.MANV
		
		DROP TABLE #TCLICHTHICB2
		
		SELECT #CBCT1.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENCBCT2'
		INTO #CBCT2
		FROM #CBCT1 LEFT JOIN #NVANDKHOA--Kết họ tên CBCOITHI2 với bảng cbcoithi
		ON #CBCT1.MANVCT2 = #NVANDKHOA.MANV
		
		DROP TABLE #CBCT1
		
		SELECT #CBCT2.*,ISNULL(#NVANDKHOA.HOTEN,'') AS 'HOTENCBCT3'
		INTO #CBCT3
		FROM #CBCT2 LEFT JOIN #NVANDKHOA--Kết họ tên CBCOITHI3 với bảng cbcoithi
		ON #CBCT2.MANVCT3 = #NVANDKHOA.MANV
		
		DROP TABLE #CBCT2
		DROP TABLE #NVANDKHOA
		
		SELECT DISTINCT TC.MALOPTC,TC.MAMON,MH.TENMON,TC.NHOM,TC.HOTENGVDAY AS 'GVGIANGDAY',TC.KHOA,TC.HOTENGVRADE AS 'GVRADE',TC.NGAYCHAM
		
		,TC.SOLUONGDE ,TC.HOTENGVCHAMTHI AS 'GVCHAMTHI'
		
		,TC.SOLUONGBAITHI,CB.MAPHONG AS 'PHONGTHI',CB.TIETBATDAU,CB.NGAYTHI,CB.SLDUTHI,CB.HOTENCBCT1 AS 'CBCT1',CB.SOLANCT1,CB.HOTENCBCT2 AS 'CBCT2',CB.SOLANCT2,CB.HOTENCBCT3 AS 'CBCT3',CB.SOLANCT3,TC.NGAYRADE
		
		INTO #TCCHUNG8
		FROM #TCCHUNG4 TC ,#TCMON MH,#CBCT3 CB
		WHERE TC.MALOPTC = CB.MALOPTC AND TC.MAMON = MH.MAMON AND  TC.MAMON = CB.MAMON
		ORDER BY CB.NGAYTHI,MH.TENMON
		
		DROP TABLE #CBCT3
		DROP TABLE #TCCHUNG4
		DROP TABLE #TCMON
		
		SELECT TC.*,
		(
			CASE
				WHEN TC.NGAYRADE = DG.NGAY AND DG.MALOAIDG ='RADE' AND TC.MAMON = DG.MAMON THEN DG.GIA
				ELSE '50000'
			END
		) AS 'DONGIARADE'
		INTO #TCCHUNG5
		FROM #TCCHUNG8 TC LEFT JOIN #DG DG
		ON TC.MAMON = DG.MAMON AND DG.MALOAIDG ='RADE'
		DROP TABLE #TCCHUNG8
		
		
		SELECT DISTINCT TC.*
		,(
			CASE
				WHEN TC.NGAYCHAM = DG.NGAY AND DG.MALOAIDG ='CHAMTHI' AND TC.MAMON = DG.MAMON THEN DG.GIA
				ELSE '5000'
			END
		) AS 'DONGIACHAMTHI'
		INTO #TCCHUNG6
		FROM #TCCHUNG5 TC LEFT JOIN #DG DG
		ON DG.MAMON = TC.MAMON AND DG.MALOAIDG ='CHAMTHI'
		
		DROP TABLE #TCCHUNG5
		
		SELECT DISTINCT TC.TENMON,TC.NHOM,ISNULL(TC.PHONGTHI,'') AS 'PHONGTHI',ISNULL(TC.TIETBATDAU,'') AS 'TIETBATDAU',TC.NGAYTHI,ISNULL(TC.SLDUTHI,'') AS 'SOLUONGDUTHI',TC.GVGIANGDAY,TC.KHOA,TC.GVRADE,TC.DONGIARADE,TC.SOLUONGDE,TC.GVCHAMTHI,TC.DONGIACHAMTHI,TC.SOLUONGBAITHI,TC.CBCT1,TC.SOLANCT1,TC.CBCT2,TC.SOLANCT2,TC.CBCT3,TC.SOLANCT3
		,(
			CASE
				WHEN TC.NGAYTHI = DG.NGAY AND DG.MALOAIDG ='COITHI' AND TC.MAMON = DG.MAMON THEN DG.GIA
				WHEN TC.TIETBATDAU > 8 OR DATEPART(DW,TC.NGAYTHI) > 6 THEN '60000'
				ELSE '50000'
			END
		) AS 'DONGIACOITHI'
		FROM #TCCHUNG6 TC LEFT JOIN #DG DG
		ON DG.MAMON = TC.MAMON  AND DG.MALOAIDG ='COITHI'
		
		DROP TABLE #DG
		DROP TABLE #TCCHUNG6
		
	
	
END
GO
/****** Object:  Default [DF_NHANVIEN_GIOITINH]    Script Date: 11/21/2019 20:58:31 ******/
ALTER TABLE [dbo].[NHANVIEN] ADD  CONSTRAINT [DF_NHANVIEN_GIOITINH]  DEFAULT ('Nam') FOR [GIOITINH]
GO
/****** Object:  Default [DF_LOPTC_SLDANGKY]    Script Date: 11/21/2019 20:58:31 ******/
ALTER TABLE [dbo].[LOPTC] ADD  CONSTRAINT [DF_LOPTC_SLDANGKY]  DEFAULT ((20)) FOR [SLDANGKY]
GO
/****** Object:  Default [DF_DETHI_SOLUONGDE]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[DETHI] ADD  CONSTRAINT [DF_DETHI_SOLUONGDE]  DEFAULT ((1)) FOR [SOLUONGDE]
GO
/****** Object:  Default [DF_CTLICHTHI_TIETBATDAU]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[CTLICHTHI] ADD  CONSTRAINT [DF_CTLICHTHI_TIETBATDAU]  DEFAULT ((1)) FOR [TIETBATDAU]
GO
/****** Object:  Default [DF_CTLICHTHI_SLDUTHI]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[CTLICHTHI] ADD  CONSTRAINT [DF_CTLICHTHI_SLDUTHI]  DEFAULT ((1)) FOR [SLDUTHI]
GO
/****** Object:  Default [DF_CTLICHTHI_DOTTHI]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[CTLICHTHI] ADD  CONSTRAINT [DF_CTLICHTHI_DOTTHI]  DEFAULT ((1)) FOR [DOTTHI]
GO
/****** Object:  Default [DF_BAITHI_MANV]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[BAITHI] ADD  CONSTRAINT [DF_BAITHI_MANV]  DEFAULT ((0)) FOR [MANV]
GO
/****** Object:  Default [DF_BAITHI_SOLUONGBAITHI]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[BAITHI] ADD  CONSTRAINT [DF_BAITHI_SOLUONGBAITHI]  DEFAULT ((0)) FOR [SOLUONGBAITHI]
GO
/****** Object:  Check [CK_Gioitinh]    Script Date: 11/21/2019 20:58:31 ******/
ALTER TABLE [dbo].[NHANVIEN]  WITH CHECK ADD  CONSTRAINT [CK_Gioitinh] CHECK  (([GIOITINH]=N'Nữ' OR [GIOITINH]='Nam'))
GO
ALTER TABLE [dbo].[NHANVIEN] CHECK CONSTRAINT [CK_Gioitinh]
GO
/****** Object:  Check [CK_hk]    Script Date: 11/21/2019 20:58:31 ******/
ALTER TABLE [dbo].[LOPTC]  WITH CHECK ADD  CONSTRAINT [CK_hk] CHECK  (([HOCKY]<=(3) AND [HOCKY]>=(1)))
GO
ALTER TABLE [dbo].[LOPTC] CHECK CONSTRAINT [CK_hk]
GO
/****** Object:  Check [CK_LOPTC]    Script Date: 11/21/2019 20:58:31 ******/
ALTER TABLE [dbo].[LOPTC]  WITH CHECK ADD  CONSTRAINT [CK_LOPTC] CHECK  (([SLDANGKY]>=(20)))
GO
ALTER TABLE [dbo].[LOPTC] CHECK CONSTRAINT [CK_LOPTC]
GO
/****** Object:  Check [CK_DETHI]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[DETHI]  WITH CHECK ADD  CONSTRAINT [CK_DETHI] CHECK  (([SOLUONGDE]>=(1)))
GO
ALTER TABLE [dbo].[DETHI] CHECK CONSTRAINT [CK_DETHI]
GO
/****** Object:  Check [CK_CTLICHTHI]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[CTLICHTHI]  WITH CHECK ADD  CONSTRAINT [CK_CTLICHTHI] CHECK  (([TIETBATDAU]>=(1) AND [TIETBATDAU]<=(12)))
GO
ALTER TABLE [dbo].[CTLICHTHI] CHECK CONSTRAINT [CK_CTLICHTHI]
GO
/****** Object:  Check [CK_CTLICHTHI_1]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[CTLICHTHI]  WITH CHECK ADD  CONSTRAINT [CK_CTLICHTHI_1] CHECK  (([SLDUTHI]>=(1)))
GO
ALTER TABLE [dbo].[CTLICHTHI] CHECK CONSTRAINT [CK_CTLICHTHI_1]
GO
/****** Object:  Check [CK_CTLICHTHI_2]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[CTLICHTHI]  WITH CHECK ADD  CONSTRAINT [CK_CTLICHTHI_2] CHECK  (([DOTTHI]>=(1) AND [DOTTHI]<=(3)))
GO
ALTER TABLE [dbo].[CTLICHTHI] CHECK CONSTRAINT [CK_CTLICHTHI_2]
GO
/****** Object:  Check [CK_BAITHI]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[BAITHI]  WITH CHECK ADD  CONSTRAINT [CK_BAITHI] CHECK  (([SOLUONGBAITHI]>=(0)))
GO
ALTER TABLE [dbo].[BAITHI] CHECK CONSTRAINT [CK_BAITHI]
GO
/****** Object:  ForeignKey [FK_TOBOMON_PBKHOA]    Script Date: 11/21/2019 20:58:31 ******/
ALTER TABLE [dbo].[TOBOMON]  WITH CHECK ADD  CONSTRAINT [FK_TOBOMON_PBKHOA] FOREIGN KEY([MAPBK])
REFERENCES [dbo].[PBKHOA] ([MAPBK])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[TOBOMON] CHECK CONSTRAINT [FK_TOBOMON_PBKHOA]
GO
/****** Object:  ForeignKey [FK_CTDONGIA_LOAIDONGIA]    Script Date: 11/21/2019 20:58:31 ******/
ALTER TABLE [dbo].[CTDONGIA]  WITH CHECK ADD  CONSTRAINT [FK_CTDONGIA_LOAIDONGIA] FOREIGN KEY([MALOAIDG])
REFERENCES [dbo].[LOAIDONGIA] ([MALOAIDG])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[CTDONGIA] CHECK CONSTRAINT [FK_CTDONGIA_LOAIDONGIA]
GO
/****** Object:  ForeignKey [FK_CTDONGIA_MON]    Script Date: 11/21/2019 20:58:31 ******/
ALTER TABLE [dbo].[CTDONGIA]  WITH CHECK ADD  CONSTRAINT [FK_CTDONGIA_MON] FOREIGN KEY([MAMON])
REFERENCES [dbo].[MON] ([MAMON])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[CTDONGIA] CHECK CONSTRAINT [FK_CTDONGIA_MON]
GO
/****** Object:  ForeignKey [FK_NHANVIEN_LOAINV]    Script Date: 11/21/2019 20:58:31 ******/
ALTER TABLE [dbo].[NHANVIEN]  WITH CHECK ADD  CONSTRAINT [FK_NHANVIEN_LOAINV] FOREIGN KEY([MALOAI])
REFERENCES [dbo].[LOAINV] ([MALOAI])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[NHANVIEN] CHECK CONSTRAINT [FK_NHANVIEN_LOAINV]
GO
/****** Object:  ForeignKey [FK_NHANVIEN_TOBOMON]    Script Date: 11/21/2019 20:58:31 ******/
ALTER TABLE [dbo].[NHANVIEN]  WITH CHECK ADD  CONSTRAINT [FK_NHANVIEN_TOBOMON] FOREIGN KEY([MATBM])
REFERENCES [dbo].[TOBOMON] ([MATBM])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[NHANVIEN] CHECK CONSTRAINT [FK_NHANVIEN_TOBOMON]
GO
/****** Object:  ForeignKey [FK_LOPTC_LOP]    Script Date: 11/21/2019 20:58:31 ******/
ALTER TABLE [dbo].[LOPTC]  WITH CHECK ADD  CONSTRAINT [FK_LOPTC_LOP] FOREIGN KEY([MALOP])
REFERENCES [dbo].[LOP] ([MALOP])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[LOPTC] CHECK CONSTRAINT [FK_LOPTC_LOP]
GO
/****** Object:  ForeignKey [FK_LOPTC_MON]    Script Date: 11/21/2019 20:58:31 ******/
ALTER TABLE [dbo].[LOPTC]  WITH CHECK ADD  CONSTRAINT [FK_LOPTC_MON] FOREIGN KEY([MAMON])
REFERENCES [dbo].[MON] ([MAMON])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[LOPTC] CHECK CONSTRAINT [FK_LOPTC_MON]
GO
/****** Object:  ForeignKey [FK_LOPTC_NHANVIEN]    Script Date: 11/21/2019 20:58:31 ******/
ALTER TABLE [dbo].[LOPTC]  WITH CHECK ADD  CONSTRAINT [FK_LOPTC_NHANVIEN] FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[LOPTC] CHECK CONSTRAINT [FK_LOPTC_NHANVIEN]
GO
/****** Object:  ForeignKey [FK_DETHI_LOPTC]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[DETHI]  WITH CHECK ADD  CONSTRAINT [FK_DETHI_LOPTC] FOREIGN KEY([MALOPTC])
REFERENCES [dbo].[LOPTC] ([MALOPTC])
GO
ALTER TABLE [dbo].[DETHI] CHECK CONSTRAINT [FK_DETHI_LOPTC]
GO
/****** Object:  ForeignKey [FK_DETHI_NHANVIEN]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[DETHI]  WITH CHECK ADD  CONSTRAINT [FK_DETHI_NHANVIEN] FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[DETHI] CHECK CONSTRAINT [FK_DETHI_NHANVIEN]
GO
/****** Object:  ForeignKey [FK_CTLICHTHI_LOPTC]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[CTLICHTHI]  WITH CHECK ADD  CONSTRAINT [FK_CTLICHTHI_LOPTC] FOREIGN KEY([MALOPTC])
REFERENCES [dbo].[LOPTC] ([MALOPTC])
GO
ALTER TABLE [dbo].[CTLICHTHI] CHECK CONSTRAINT [FK_CTLICHTHI_LOPTC]
GO
/****** Object:  ForeignKey [FK_CTLICHTHI_PHONG]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[CTLICHTHI]  WITH CHECK ADD  CONSTRAINT [FK_CTLICHTHI_PHONG] FOREIGN KEY([MAPHONG])
REFERENCES [dbo].[PHONG] ([MAPHONG])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[CTLICHTHI] CHECK CONSTRAINT [FK_CTLICHTHI_PHONG]
GO
/****** Object:  ForeignKey [FK_BAITHI_LOPTC1]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[BAITHI]  WITH CHECK ADD  CONSTRAINT [FK_BAITHI_LOPTC1] FOREIGN KEY([MALOPTC])
REFERENCES [dbo].[LOPTC] ([MALOPTC])
GO
ALTER TABLE [dbo].[BAITHI] CHECK CONSTRAINT [FK_BAITHI_LOPTC1]
GO
/****** Object:  ForeignKey [FK_BAITHI_NHANVIEN]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[BAITHI]  WITH CHECK ADD  CONSTRAINT [FK_BAITHI_NHANVIEN] FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[BAITHI] CHECK CONSTRAINT [FK_BAITHI_NHANVIEN]
GO
/****** Object:  ForeignKey [FK_CBCOITHI_CTLICHTHI]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[CBCOITHI]  WITH CHECK ADD  CONSTRAINT [FK_CBCOITHI_CTLICHTHI] FOREIGN KEY([MAPHONG], [NGAYTHI], [TIETBATDAU])
REFERENCES [dbo].[CTLICHTHI] ([MAPHONG], [NGAYTHI], [TIETBATDAU])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CBCOITHI] CHECK CONSTRAINT [FK_CBCOITHI_CTLICHTHI]
GO
/****** Object:  ForeignKey [FK_CBCOITHI_NHANVIEN]    Script Date: 11/21/2019 20:58:33 ******/
ALTER TABLE [dbo].[CBCOITHI]  WITH CHECK ADD  CONSTRAINT [FK_CBCOITHI_NHANVIEN] FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[CBCOITHI] CHECK CONSTRAINT [FK_CBCOITHI_NHANVIEN]
GO
