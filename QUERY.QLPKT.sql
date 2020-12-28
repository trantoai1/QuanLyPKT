﻿ALTER PROC SP_LICH_THI
@DOTTHI int, @HOCKY INT, @NIENKHOA NVARCHAR(12)
AS
BEGIN
SELECT * INTO #LTC
FROM LOPTC
WHERE @NIENKHOA= LOPTC.NIENKHOA AND @HOCKY = LOPTC.HOCKY 

SELECT LT.NGAYTHI,LT.MAPHONG,LT.TIETBATDAU,LT.MALOPTC INTO #LT
FROM CTLICHTHI LT
WHERE   @DOTTHI =LT.DOTTHI
 
SELECT TC.MALOP, MON.MAMON, MON.TENMON, NV.HO + ' '+ NV.TEN AS HOTEN, TC.NHOM, TC.SLDANGKY, LT.NGAYTHI, LT.MAPHONG, LT.TIETBATDAU
FROM #LTC TC, #LT LT, NHANVIEN NV, LOP, MON
WHERE TC.MALOPTC=LT.MALOPTC AND NV.MANV=TC.MANV AND LOP.MALOP=TC.MALOP AND MON.MAMON=TC.MAMON
END
EXEC SP_LICH_THI '1','1','2019'

CREATE VIEW V_LOPTC
AS
SELECT dbo.MON.TENMON, dbo.LOPTC.NIENKHOA, dbo.LOPTC.HOCKY, dbo.LOPTC.NHOM, dbo.LOPTC.MALOPTC, dbo.NHANVIEN.HO + ' ' + dbo.NHANVIEN.TEN AS 'HOTEN'
FROM dbo.LOPTC INNER JOIN
                      dbo.MON ON dbo.LOPTC.MAMON = dbo.MON.MAMON INNER JOIN
                      dbo.NHANVIEN ON dbo.LOPTC.MANV = dbo.NHANVIEN.MANV

GO


ALTER PROC SP_TO_THEO_KHOA 
@TENPBK nvarchar(50)
AS 
SELECT TBM.TENTBM
FROM TOBOMON AS TBM  INNER JOIN PBKHOA AS PB
ON TBM.MAPBK=PB.MAPBK AND PB.TENPBK=@TENPBK
GO
EXEC SP_TO_THEO_KHOA N'Khoa cơ bản 2'

GO

ALTER PROC SP_TEN_THEO_TO 
@TENTBM nvarchar(50)
AS 
SELECT NHANVIEN.HO + ' '+NHANVIEN.TEN + '_' +NHANVIEN.MANV as TENGV
FROM NHANVIEN  INNER JOIN TOBOMON 
ON NHANVIEN.MATBM=TOBOMON.MATBM AND TOBOMON.TENTBM=@TENTBM
GO
EXEC SP_TEN_THEO_TO  N'Tiếng Anh'

CREATE VIEW V_TENGIANGVIEN
AS
SELECT NHANVIEN.HO + ' ' +NHANVIEN.TEN AS 'HOTEN', DETHI.MANV
FROM NHANVIEN INNER JOIN DETHI 
ON NHANVIEN.MANV = DETHI.MANV



CREATE PROC SP_TENGV
@HO nvarchar(50), @TEN nvarchar(20), @MANV nchar(10)
AS
SELECT NHANVIEN.HO + ' ' + NHANVIEN.TEN AS HOTEN, DETHI.MANV FROM NHANVIEN,DETHI WHERE NHANVIEN.MANV=DETHI.MANV AND NHANVIEN.HO LIKE 'G%'
EXEC  SP_TENGV 'Trần Văn' , 'An', 'GV01'

CREATE PROC_DETHI
SELECT PBKHOA.TENPBK, TBM.TENTBM, V_TENGIANGVIEN.*, 
FROM PBKHOA, TOBOMON, V_TENGIANGVIEN



CREATE PROC SP_GV
@ nvarchar(50), @TEN nvarchar(20), @MANV nchar(10)
AS
SELECT NHANVIEN.HO + ' ' + NHANVIEN.TEN AS HOTEN, DETHI.MANV FROM NHANVIEN,DETHI WHERE NHANVIEN.MANV=DETHI.MANV AND NHANVIEN.HO LIKE 'G%'
EXEC  SP_TENGV 'Trần Văn' , 'An', 'GV01'

CREATE VIEW VK_HIENTHITHONGTIN
AS
SELECT HOTEN = N.HO+N.TEN, N.MANV,  D.MALOPTC,D.SOLUONGDE
FROM DETHI AS D INNER JOIN NHANVIEN AS N 
ON D.MANV = N.MANV

create view v_tbleDeThi
as
select N.HO +' '+N.TEN AS 'Ten giang vien', D.MANV, D.MALOPTC, D.SOLUONGDE
from DETHI AS D INNER JOIN NHANVIEN AS N ON N.MANV=D.MANV INNER JOIN LOPTC AS L ON L.MALOPTC=D.MALOPTC


