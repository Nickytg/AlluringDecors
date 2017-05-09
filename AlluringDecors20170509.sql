USE [master]
GO
/****** Object:  Database [AlluringDecors]    Script Date: 5/9/2017 7:49:29 PM ******/
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
/****** Object:  Table [dbo].[Account]    Script Date: 5/9/2017 7:49:29 PM ******/
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
/****** Object:  Table [dbo].[Bill]    Script Date: 5/9/2017 7:49:29 PM ******/
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
/****** Object:  Table [dbo].[ContactUs]    Script Date: 5/9/2017 7:49:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ContactUs](
	[ContactUsID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Phone] [nvarchar](20) NULL,
	[Address] [nvarchar](50) NULL,
	[Content] [nvarchar](250) NULL,
 CONSTRAINT [PK_ContactUs] PRIMARY KEY CLUSTERED 
(
	[ContactUsID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Description]    Script Date: 5/9/2017 7:49:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Description](
	[DescriptionID] [int] IDENTITY(1,1) NOT NULL,
	[DescriptionTypeID] [int] NULL,
	[Content] [nvarchar](250) NULL,
 CONSTRAINT [PK_Description] PRIMARY KEY CLUSTERED 
(
	[DescriptionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DescriptionType]    Script Date: 5/9/2017 7:49:29 PM ******/
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
/****** Object:  Table [dbo].[Domain]    Script Date: 5/9/2017 7:49:29 PM ******/
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
/****** Object:  Table [dbo].[FAQ]    Script Date: 5/9/2017 7:49:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FAQ](
	[FAQID] [int] IDENTITY(1,1) NOT NULL,
	[Question] [nvarchar](50) NULL,
	[Answer] [nvarchar](50) NULL,
 CONSTRAINT [PK_FAQ] PRIMARY KEY CLUSTERED 
(
	[FAQID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[FeedBack]    Script Date: 5/9/2017 7:49:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FeedBack](
	[FeedBackID] [int] IDENTITY(1,1) NOT NULL,
	[Question] [nvarchar](50) NULL,
	[Answer] [nvarchar](50) NULL,
	[UserID] [int] NULL,
 CONSTRAINT [PK_FeedBack] PRIMARY KEY CLUSTERED 
(
	[FeedBackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PaymentDetail]    Script Date: 5/9/2017 7:49:29 PM ******/
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
/****** Object:  Table [dbo].[Role]    Script Date: 5/9/2017 7:49:29 PM ******/
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
/****** Object:  Table [dbo].[ServicesOffered]    Script Date: 5/9/2017 7:49:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ServicesOffered](
	[ServicesOfferedID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[DomainID] [int] NULL,
	[Content] [nvarchar](250) NULL,
 CONSTRAINT [PK_ServicesOffered] PRIMARY KEY CLUSTERED 
(
	[ServicesOfferedID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ServicesRequest]    Script Date: 5/9/2017 7:49:29 PM ******/
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
/****** Object:  Table [dbo].[ServicesRequestStatus]    Script Date: 5/9/2017 7:49:29 PM ******/
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
/****** Object:  Table [dbo].[User]    Script Date: 5/9/2017 7:49:29 PM ******/
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
INSERT [dbo].[Account] ([UserID], [Username], [Password], [RoleID]) VALUES (1, N'admin', N'admin', 1)
INSERT [dbo].[Account] ([UserID], [Username], [Password], [RoleID]) VALUES (13, N'asdasd', N'sadasd', 2)
INSERT [dbo].[Account] ([UserID], [Username], [Password], [RoleID]) VALUES (11, N'guest', N'guest', 2)
SET IDENTITY_INSERT [dbo].[Bill] ON 

INSERT [dbo].[Bill] ([BillID], [ServicesRequestID]) VALUES (2, 1)
SET IDENTITY_INSERT [dbo].[Bill] OFF
SET IDENTITY_INSERT [dbo].[Description] ON 

INSERT [dbo].[Description] ([DescriptionID], [DescriptionTypeID], [Content]) VALUES (1, 1, N'<p>Company description</p>

<p>OH YEYEYEYE</p>
')
INSERT [dbo].[Description] ([DescriptionID], [DescriptionTypeID], [Content]) VALUES (2, 2, N'Services')
INSERT [dbo].[Description] ([DescriptionID], [DescriptionTypeID], [Content]) VALUES (3, 3, N'Project')
INSERT [dbo].[Description] ([DescriptionID], [DescriptionTypeID], [Content]) VALUES (4, 4, N'<p><strong>asdasdasdReason why choose us</strong></p>

<p><strong>Olala this is cool</strong></p>
')
INSERT [dbo].[Description] ([DescriptionID], [DescriptionTypeID], [Content]) VALUES (5, 5, N'ABOUT')
SET IDENTITY_INSERT [dbo].[Description] OFF
SET IDENTITY_INSERT [dbo].[DescriptionType] ON 

INSERT [dbo].[DescriptionType] ([DescriptionTypeID], [Name]) VALUES (1, N'company')
INSERT [dbo].[DescriptionType] ([DescriptionTypeID], [Name]) VALUES (2, N'services')
INSERT [dbo].[DescriptionType] ([DescriptionTypeID], [Name]) VALUES (3, N'project')
INSERT [dbo].[DescriptionType] ([DescriptionTypeID], [Name]) VALUES (4, N'reason')
INSERT [dbo].[DescriptionType] ([DescriptionTypeID], [Name]) VALUES (5, N'aboutUs')
SET IDENTITY_INSERT [dbo].[DescriptionType] OFF
SET IDENTITY_INSERT [dbo].[Domain] ON 

INSERT [dbo].[Domain] ([DomainID], [Name]) VALUES (2, N'Restaurant')
SET IDENTITY_INSERT [dbo].[Domain] OFF
SET IDENTITY_INSERT [dbo].[FAQ] ON 

INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (1, N'How can I finish this?', N'No way')
INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (2, N'aaa', N'bbb')
INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (3, N'asda', N'sdas')
INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (4, N'asdccc', N'cczxc')
SET IDENTITY_INSERT [dbo].[FAQ] OFF
SET IDENTITY_INSERT [dbo].[FeedBack] ON 

INSERT [dbo].[FeedBack] ([FeedBackID], [Question], [Answer], [UserID]) VALUES (2, N'Are you crazy', N'Yesa', 1)
INSERT [dbo].[FeedBack] ([FeedBackID], [Question], [Answer], [UserID]) VALUES (3, N'asdas', N'dasdas', 1)
SET IDENTITY_INSERT [dbo].[FeedBack] OFF
SET IDENTITY_INSERT [dbo].[PaymentDetail] ON 

INSERT [dbo].[PaymentDetail] ([PaymentDetaiID], [BillID], [Date], [TotalBillAmount], [DueAmount], [BalanceAmount], [TotalPaidAmount], [isMaintained]) VALUES (1, 2, CAST(0x0000A76E00A34A6C AS DateTime), 0, 0, 0, 0, 0)
SET IDENTITY_INSERT [dbo].[PaymentDetail] OFF
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([RoleID], [Name]) VALUES (1, N'administrator')
INSERT [dbo].[Role] ([RoleID], [Name]) VALUES (2, N'guest')
INSERT [dbo].[Role] ([RoleID], [Name]) VALUES (3, N'client')
SET IDENTITY_INSERT [dbo].[Role] OFF
SET IDENTITY_INSERT [dbo].[ServicesOffered] ON 

INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (1, N'Glass Decoration', 2, N'<p>This is the description of Glass Decoration for Restaurant</p>

<p><img alt="" src="http://images.fonearena.com/blog/wp-content/uploads/2013/11/Lenovo-p780-camera-sample-10.jpg" /></p>
')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (2, N'Wood Decoration', 2, N'no')
SET IDENTITY_INSERT [dbo].[ServicesOffered] OFF
SET IDENTITY_INSERT [dbo].[ServicesRequest] ON 

INSERT [dbo].[ServicesRequest] ([ServicesRequestID], [ServicesOfferedID], [UserID], [ServicesRequestStatusID], [Remark]) VALUES (1, 1, 1, 1, N'test')
SET IDENTITY_INSERT [dbo].[ServicesRequest] OFF
SET IDENTITY_INSERT [dbo].[ServicesRequestStatus] ON 

INSERT [dbo].[ServicesRequestStatus] ([ServicesRequestStatusID], [Name]) VALUES (1, N'On-Going')
SET IDENTITY_INSERT [dbo].[ServicesRequestStatus] OFF
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [Address], [Phone]) VALUES (1, N'Bao', N'Nguyen', N'thaibao@gmail.com', N'LVH Street', N'01920912312')
INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [Address], [Phone]) VALUES (11, N'Bao G', N'Nguyen G', N'g@gmail.com', N'g address', N'12341414141')
INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [Address], [Phone]) VALUES (13, N'Bao', N'Nguyen', N'thaibao', N'asdasd', N'asd')
SET IDENTITY_INSERT [dbo].[User] OFF
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
