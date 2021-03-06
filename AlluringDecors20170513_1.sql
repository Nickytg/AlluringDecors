USE [master]
GO
/****** Object:  Database [AlluringDecors]    Script Date: 5/13/2017 6:02:01 PM ******/
CREATE DATABASE [AlluringDecors]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'AlluringDecors', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\AlluringDecors.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'AlluringDecors_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\AlluringDecors_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [AlluringDecors] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [AlluringDecors].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [AlluringDecors] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [AlluringDecors] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [AlluringDecors] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [AlluringDecors] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [AlluringDecors] SET ARITHABORT OFF 
GO
ALTER DATABASE [AlluringDecors] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [AlluringDecors] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [AlluringDecors] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [AlluringDecors] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [AlluringDecors] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [AlluringDecors] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [AlluringDecors] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [AlluringDecors] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [AlluringDecors] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [AlluringDecors] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [AlluringDecors] SET  DISABLE_BROKER 
GO
ALTER DATABASE [AlluringDecors] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [AlluringDecors] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [AlluringDecors] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [AlluringDecors] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [AlluringDecors] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [AlluringDecors] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [AlluringDecors] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [AlluringDecors] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [AlluringDecors] SET  MULTI_USER 
GO
ALTER DATABASE [AlluringDecors] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [AlluringDecors] SET DB_CHAINING OFF 
GO
ALTER DATABASE [AlluringDecors] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [AlluringDecors] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [AlluringDecors]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 5/13/2017 6:02:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[UserID] [int] NULL,
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NULL,
	[RoleID] [int] NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Bill]    Script Date: 5/13/2017 6:02:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill](
	[BillID] [int] IDENTITY(1,1) NOT NULL,
	[ServicesRequestID] [int] NULL,
 CONSTRAINT [PK_Bill] PRIMARY KEY CLUSTERED 
(
	[BillID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ContactUs]    Script Date: 5/13/2017 6:02:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ContactUs](
	[ContactUsID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Phone] [nvarchar](20) NULL,
	[Address] [nvarchar](50) NULL,
	[Content] [text] NULL,
 CONSTRAINT [PK_ContactUs] PRIMARY KEY CLUSTERED 
(
	[ContactUsID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Description]    Script Date: 5/13/2017 6:02:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Description](
	[DescriptionID] [int] IDENTITY(1,1) NOT NULL,
	[DescriptionTypeID] [int] NULL,
	[Content] [text] NULL,
 CONSTRAINT [PK_Description] PRIMARY KEY CLUSTERED 
(
	[DescriptionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DescriptionType]    Script Date: 5/13/2017 6:02:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DescriptionType](
	[DescriptionTypeID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
 CONSTRAINT [PK_DescriptionType] PRIMARY KEY CLUSTERED 
(
	[DescriptionTypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Domain]    Script Date: 5/13/2017 6:02:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Domain](
	[DomainID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Domain] PRIMARY KEY CLUSTERED 
(
	[DomainID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[FAQ]    Script Date: 5/13/2017 6:02:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FAQ](
	[FAQID] [int] IDENTITY(1,1) NOT NULL,
	[Question] [text] NULL,
	[Answer] [text] NULL,
 CONSTRAINT [PK_FAQ] PRIMARY KEY CLUSTERED 
(
	[FAQID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[FeedBack]    Script Date: 5/13/2017 6:02:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FeedBack](
	[FeedBackID] [int] IDENTITY(1,1) NOT NULL,
	[Question] [text] NULL,
	[Answer] [text] NULL,
	[UserID] [int] NULL,
 CONSTRAINT [PK_FeedBack] PRIMARY KEY CLUSTERED 
(
	[FeedBackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PaymentDetail]    Script Date: 5/13/2017 6:02:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PaymentDetail](
	[PaymentDetaiID] [int] IDENTITY(1,1) NOT NULL,
	[BillID] [int] NULL,
	[Date] [datetime] NULL,
	[TotalBillAmount] [float] NULL,
	[DueAmount] [float] NULL,
	[BalanceAmount] [float] NULL,
	[TotalPaidAmount] [float] NULL,
	[isMaintained] [bit] NULL,
 CONSTRAINT [PK_PaymentDetail] PRIMARY KEY CLUSTERED 
(
	[PaymentDetaiID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role]    Script Date: 5/13/2017 6:02:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Role](
	[RoleID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ServicesOffered]    Script Date: 5/13/2017 6:02:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ServicesOffered](
	[ServicesOfferedID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[DomainID] [int] NULL,
	[Content] [text] NULL,
 CONSTRAINT [PK_ServicesOffered] PRIMARY KEY CLUSTERED 
(
	[ServicesOfferedID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ServicesRequest]    Script Date: 5/13/2017 6:02:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ServicesRequest](
	[ServicesRequestID] [int] IDENTITY(1,1) NOT NULL,
	[ServicesOfferedID] [int] NULL,
	[UserID] [int] NULL,
	[ServicesRequestStatusID] [int] NULL,
	[Remark] [nvarchar](250) NULL,
 CONSTRAINT [PK_ServicesRequest] PRIMARY KEY CLUSTERED 
(
	[ServicesRequestID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ServicesRequestStatus]    Script Date: 5/13/2017 6:02:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ServicesRequestStatus](
	[ServicesRequestStatusID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
 CONSTRAINT [PK_ServicesRequestStatus] PRIMARY KEY CLUSTERED 
(
	[ServicesRequestStatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[User]    Script Date: 5/13/2017 6:02:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[User](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [varchar](50) NULL,
	[LastName] [varchar](50) NULL,
	[Email] [varchar](50) NULL,
	[Address] [varchar](50) NULL,
	[Phone] [varchar](20) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Role] FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([RoleID])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Role]
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_User]
GO
ALTER TABLE [dbo].[Description]  WITH CHECK ADD  CONSTRAINT [FK_Description_DescriptionType] FOREIGN KEY([DescriptionTypeID])
REFERENCES [dbo].[DescriptionType] ([DescriptionTypeID])
GO
ALTER TABLE [dbo].[Description] CHECK CONSTRAINT [FK_Description_DescriptionType]
GO
ALTER TABLE [dbo].[FeedBack]  WITH CHECK ADD  CONSTRAINT [FK_FeedBack_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[FeedBack] CHECK CONSTRAINT [FK_FeedBack_User]
GO
ALTER TABLE [dbo].[PaymentDetail]  WITH CHECK ADD  CONSTRAINT [FK_PaymentDetail_Bill] FOREIGN KEY([BillID])
REFERENCES [dbo].[Bill] ([BillID])
GO
ALTER TABLE [dbo].[PaymentDetail] CHECK CONSTRAINT [FK_PaymentDetail_Bill]
GO
ALTER TABLE [dbo].[ServicesOffered]  WITH CHECK ADD  CONSTRAINT [FK_ServicesOffered_Domain] FOREIGN KEY([DomainID])
REFERENCES [dbo].[Domain] ([DomainID])
GO
ALTER TABLE [dbo].[ServicesOffered] CHECK CONSTRAINT [FK_ServicesOffered_Domain]
GO
ALTER TABLE [dbo].[ServicesRequest]  WITH CHECK ADD  CONSTRAINT [FK_ServicesRequest_ServicesOffered] FOREIGN KEY([ServicesOfferedID])
REFERENCES [dbo].[ServicesOffered] ([ServicesOfferedID])
GO
ALTER TABLE [dbo].[ServicesRequest] CHECK CONSTRAINT [FK_ServicesRequest_ServicesOffered]
GO
ALTER TABLE [dbo].[ServicesRequest]  WITH CHECK ADD  CONSTRAINT [FK_ServicesRequest_ServicesRequestStatus] FOREIGN KEY([ServicesRequestStatusID])
REFERENCES [dbo].[ServicesRequestStatus] ([ServicesRequestStatusID])
GO
ALTER TABLE [dbo].[ServicesRequest] CHECK CONSTRAINT [FK_ServicesRequest_ServicesRequestStatus]
GO
ALTER TABLE [dbo].[ServicesRequest]  WITH CHECK ADD  CONSTRAINT [FK_ServicesRequest_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[ServicesRequest] CHECK CONSTRAINT [FK_ServicesRequest_User]
GO
USE [master]
GO
ALTER DATABASE [AlluringDecors] SET  READ_WRITE 
GO
