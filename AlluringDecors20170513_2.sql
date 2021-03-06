USE [master]
GO
/****** Object:  Database [AlluringDecors]    Script Date: 5/13/2017 7:21:19 PM ******/
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
/****** Object:  Table [dbo].[Account]    Script Date: 5/13/2017 7:21:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[UserID] [int] NOT NULL,
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[RoleID] [int] NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Bill]    Script Date: 5/13/2017 7:21:20 PM ******/
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
/****** Object:  Table [dbo].[ContactUs]    Script Date: 5/13/2017 7:21:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ContactUs](
	[ContactUsID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Phone] [nvarchar](20) NOT NULL,
	[Address] [nvarchar](50) NOT NULL,
	[Content] [text] NOT NULL,
 CONSTRAINT [PK_ContactUs] PRIMARY KEY CLUSTERED 
(
	[ContactUsID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Description]    Script Date: 5/13/2017 7:21:20 PM ******/
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
/****** Object:  Table [dbo].[DescriptionType]    Script Date: 5/13/2017 7:21:20 PM ******/
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
/****** Object:  Table [dbo].[Domain]    Script Date: 5/13/2017 7:21:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Domain](
	[DomainID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Domain] PRIMARY KEY CLUSTERED 
(
	[DomainID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[FAQ]    Script Date: 5/13/2017 7:21:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FAQ](
	[FAQID] [int] IDENTITY(1,1) NOT NULL,
	[Question] [text] NOT NULL,
	[Answer] [text] NOT NULL,
 CONSTRAINT [PK_FAQ] PRIMARY KEY CLUSTERED 
(
	[FAQID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[FeedBack]    Script Date: 5/13/2017 7:21:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FeedBack](
	[FeedBackID] [int] IDENTITY(1,1) NOT NULL,
	[Question] [text] NOT NULL,
	[Answer] [text] NOT NULL,
	[UserID] [int] NULL,
 CONSTRAINT [PK_FeedBack] PRIMARY KEY CLUSTERED 
(
	[FeedBackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PaymentDetail]    Script Date: 5/13/2017 7:21:20 PM ******/
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
/****** Object:  Table [dbo].[Role]    Script Date: 5/13/2017 7:21:20 PM ******/
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
/****** Object:  Table [dbo].[ServicesOffered]    Script Date: 5/13/2017 7:21:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ServicesOffered](
	[ServicesOfferedID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[DomainID] [int] NULL,
	[Content] [text] NOT NULL,
 CONSTRAINT [PK_ServicesOffered] PRIMARY KEY CLUSTERED 
(
	[ServicesOfferedID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ServicesRequest]    Script Date: 5/13/2017 7:21:20 PM ******/
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
/****** Object:  Table [dbo].[ServicesRequestStatus]    Script Date: 5/13/2017 7:21:20 PM ******/
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
/****** Object:  Table [dbo].[User]    Script Date: 5/13/2017 7:21:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[User](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [varchar](50) NOT NULL,
	[LastName] [varchar](50) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[Address] [varchar](50) NOT NULL,
	[Phone] [varchar](20) NOT NULL,
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
INSERT [dbo].[Account] ([UserID], [Username], [Password], [RoleID]) VALUES (15, N'client', N'client', 3)
INSERT [dbo].[Account] ([UserID], [Username], [Password], [RoleID]) VALUES (11, N'guest', N'guest', 2)
INSERT [dbo].[Account] ([UserID], [Username], [Password], [RoleID]) VALUES (1015, N'zxcz', N'xcxzc', 2)
INSERT [dbo].[Account] ([UserID], [Username], [Password], [RoleID]) VALUES (1016, N'zxcz123', N'xcxzc123', 2)
SET IDENTITY_INSERT [dbo].[Bill] ON 

INSERT [dbo].[Bill] ([BillID], [ServicesRequestID]) VALUES (2, 1)
SET IDENTITY_INSERT [dbo].[Bill] OFF
SET IDENTITY_INSERT [dbo].[ContactUs] ON 

INSERT [dbo].[ContactUs] ([ContactUsID], [Name], [Phone], [Address], [Content]) VALUES (1, N'BAOBAO', N'123123123', N'asdasd', N'asdasdasdasd')
SET IDENTITY_INSERT [dbo].[ContactUs] OFF
SET IDENTITY_INSERT [dbo].[Description] ON 

INSERT [dbo].[Description] ([DescriptionID], [DescriptionTypeID], [Content]) VALUES (1, 1, N'<p><strong>Company description</strong></p>

<p>The fraud of the briefcase companies dealt a heavy blow to Venezuela when they requested, obtained and then disappeared some 20,000 million dollars at a preferential rate for fictitious imports, according to Edm&eacute;e Betancourt, or 60,000 million dollars of currency in the past few years, according to President Maduro.1</p>
')
INSERT [dbo].[Description] ([DescriptionID], [DescriptionTypeID], [Content]) VALUES (2, 2, N'<p><strong>Services description</strong></p>

<ul>
	<li>Determine the client&rsquo;s goals and requirements of the project</li>
	<li>Consider how the space will be used and how people will move through the space</li>
	<li>Sketch preliminary design plans, including electrical layouts</li>
	<li>Specify materials and furnishings, such as lighting, furniture, wall finishes, flooring, and plumbing fixtures</li>
	<li>Prepare final plans, using computer applications</li>
	<li>Create a timeline for the interior design project and estimate project costs</li>
	<li>Place orders for materials and oversee installing the design elements</li>
	<li>Visit after the project to ensure that the client is satisfied</li>
</ul>
')
INSERT [dbo].[Description] ([DescriptionID], [DescriptionTypeID], [Content]) VALUES (3, 3, N'<p><strong>Project description</strong></p>

<ul>
	<li>Decorating and Furnishing with Furniture And Glass</li>
	<li>Kitchen Design</li>
	<li>Flooring Layout</li>
	<li>Lightning Effects</li>
	<li>Window Coverings</li>
	<li>Colour Schemes</li>
	<li>Curtain Designing</li>
	<li>Architectural design</li>
	<li>Planting</li>
	<li>Seating Alignment in the Living Rooms, etc.</li>
</ul>
')
INSERT [dbo].[Description] ([DescriptionID], [DescriptionTypeID], [Content]) VALUES (4, 4, N'<p><strong>Alluring Decors </strong>strives to have the highest customer satisfaction of any painting company in the local area. We have four guiding principles that govern our actions and define our mindset. These core tenets are; customer service, detailed&nbsp;workmanship, quality materials and responsibility. Below, you will find a more detailed description of how we put each of these principles into action.</p>

<ul>
	<li><em>Friendly &amp; Accommodating Staff</em> &ndash; Our estimators and project managers will be available to you from first contact to job completion and beyond. Our goal is to make the process of painting your home as quick and easy as possible. All of our employees are trained to put the customer first.</li>
	<li><em>Excellent Communication</em> &ndash; We return phone calls, emails, texts and estimates promptly. We also make sure that you are involved throughout the painting process as much as you want to be and that you know what to expect from the beginning.</li>
	<li><em>Quality Assurance Program</em> &ndash; We are in constant communication&nbsp;with our customers throughout the painting process and even long after the paint has dried to ensure the highest satisfaction.</li>
</ul>

<p><strong>Quality Materials</strong></p>

<ul>
	<li><em>Eco-friendly</em> &ndash; We can use low and no-VOC paints for interior applications and use water-based paints by default. We use natural and non-toxic materials whenever possible. We use our materials efficiently to reduce our environmental impact.</li>
	<li><em>High Quality</em> &ndash; We believe the beauty and longevity that our customers obtain from using high quality products makes up for the marginally higher upfront cost of using the best quality paints.</li>
	<li><em>Customized</em> &ndash; We customize the&nbsp;materials we use to fit the specific needs of your application. The type of material to be painted, its current condition and the amount of traffic it will encounter all play a role in the types of prep materials, primers, and paints we chose to use for your project.</li>
</ul>

<p><strong>Responsibility</strong></p>

<ul>
	<li><em>Fully Insured</em> &ndash; Next Gen Painting is fully insured for our customer&rsquo;s and our employee&rsquo;s protection.</li>
	<li><em>Employee Background Checks</em> &ndash; Prior to hiring any of our employees, we run full background checks to ensure that we are hiring only top tier people.</li>
	<li><em>Employee Drug Tests</em> &ndash; We drug test every employee prior to hiring and randomly throughout their employment with us to ensure adherence to our strict drug policy.</li>
	<li><em>Occupational Safety and Health Administration (OSHA) Safety Standards</em> &ndash; We take the safety of our employees and customers very seriously at Next Gen Painting which is why we follow the strict guidelines of the OSHA workplace safety standards.</li>
</ul>
')
INSERT [dbo].[Description] ([DescriptionID], [DescriptionTypeID], [Content]) VALUES (5, 5, N'<p>&ldquo;<strong>Alluring Decors</strong>&rdquo; is one of the upcoming Interior and Exterior Designers in the territory. We had been in to this profession since 5 years down the line. We deal with the Interior and Exterior Decoration of the houses, offices, shops, malls, etc. based on the contract that they had received from their clients.</p>

<p>The cornerstone of our philosophy is to offer a handpicked selection of items from well-established brands and young emerging designers. By blending classic with new we hope to make our selection more interesting for you &ndash; and of course we hope to help young designers with the first steps of their career, too.</p>

<p>Classic or brand new - all our products have something in common: high quality, innovative design and authorized manufacturers. Not only our products are of the highest quality, but we also thrive to meet the highest possible quality standards in our customer service. Our biggest reward is a happy and satisfied customer.</p>

<p>Some image from projects we have done.</p>

<hr />
<p>&nbsp;</p>

<table border="0" cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td>
			<p><img alt="" src="http://www.krusto.ru/wp-content/uploads/2010/12/1261.jpg" style="height:352px; width:554px" /></p>
			</td>
			<td>
			<p><img alt="" src="http://www.robmills.com.au/wp-content/uploads/Rob-Mills-Interiors-1024x640-SorrentoLiving2.jpg" style="height:346px; width:554px" /></p>
			</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<p>Thank you for shopping with us! If you have anything in your mind let us know about it. We are here to serve you!</p>
')
SET IDENTITY_INSERT [dbo].[Description] OFF
SET IDENTITY_INSERT [dbo].[DescriptionType] ON 

INSERT [dbo].[DescriptionType] ([DescriptionTypeID], [Name]) VALUES (1, N'company')
INSERT [dbo].[DescriptionType] ([DescriptionTypeID], [Name]) VALUES (2, N'services')
INSERT [dbo].[DescriptionType] ([DescriptionTypeID], [Name]) VALUES (3, N'project')
INSERT [dbo].[DescriptionType] ([DescriptionTypeID], [Name]) VALUES (4, N'reason')
INSERT [dbo].[DescriptionType] ([DescriptionTypeID], [Name]) VALUES (5, N'aboutUs')
SET IDENTITY_INSERT [dbo].[DescriptionType] OFF
SET IDENTITY_INSERT [dbo].[Domain] ON 

INSERT [dbo].[Domain] ([DomainID], [Name]) VALUES (2, N'Restaurants')
INSERT [dbo].[Domain] ([DomainID], [Name]) VALUES (4, N'House')
INSERT [dbo].[Domain] ([DomainID], [Name]) VALUES (5, N'Offices')
INSERT [dbo].[Domain] ([DomainID], [Name]) VALUES (6, N'Community Halls')
SET IDENTITY_INSERT [dbo].[Domain] OFF
SET IDENTITY_INSERT [dbo].[FAQ] ON 

INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (5, N'How to register with the site?', N'answer')
INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (6, N'How to login into the application?', N'answer')
INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (7, N'I am not a registered user? Will I be able to apply for the service?', N'No, only the registered users will be able to apply for the service.')
INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (8, N'How to apply for the Service?', N'Answer')
INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (9, N'What are the charges of the service?', N'Answer')
INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (10, N'I reside at a location ‘X’, will I be provided with the service? Ans: First you must apply for the service mentioning the service and the domain interested in, and the address at which you want the service to be provided. Then we will get back to you whether we will provide the service at that location or not.', N'Answer')
INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (11, N'Will I be provided in any other services apart from the services and domains displayed? And if yes, what will be the charges?', N'Well it will be dependant on the type of the service and the domain you requested. We will revert back to you once the service request is received by us. The charges will be based on the service and the domain you preferred. This will be communicated well in advance before accepting and going ahead with the service.')
INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (12, N'How will I know that whether you will provide the service or not? If yes, then when will the service provided?', N'Once the service request is received, we will get back to you like whether we provide the service or not, and if we provide service we will inform when the service will be started.')
INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (13, N'How long will it take to complete the service?', N'Well this will be dependant on the service and domain preferred. Also it will be dependant on the work or building or complex, etc. for which the services are preferred for.')
INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (14, N'How will I know about the total charges for the service preferred?', N'At the time of booking for a service itself, you can find the charges for the service provided.')
INSERT [dbo].[FAQ] ([FAQID], [Question], [Answer]) VALUES (15, N'On what factors will the charges for the service depend upon?', N'The charges will be dependant on the type of the domain, and type of the service, area on which the services are preferred.')
SET IDENTITY_INSERT [dbo].[FAQ] OFF
SET IDENTITY_INSERT [dbo].[FeedBack] ON 

INSERT [dbo].[FeedBack] ([FeedBackID], [Question], [Answer], [UserID]) VALUES (2, N'You’re doing great', N'Your loyal customer base is outstanding. Customers often ask for you by name and you have a lot of repeat customers as a result. Great job! ', 1)
INSERT [dbo].[FeedBack] ([FeedBackID], [Question], [Answer], [UserID]) VALUES (3, N'Tips to improve', N'You need to aim at providing constructive and timely advice to our customers. They need to feel like they are understood.', 1)
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
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (7, N'Window Coverings', 4, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (8, N'Colour Schemes', 4, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (11, N'Planting', 4, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (12, N'Seating Alignment in the Living Rooms', 4, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (13, N'Decorating and Furnishing with Furniture and Glass', 5, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (14, N'Flooring Layout', 5, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (15, N'Lightning Effects', 5, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (16, N'Window Coverings', 5, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (17, N'Colour Schemes', 5, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (18, N'Curtain Designing', 5, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (19, N'Architectural design', 5, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (20, N'Planting', 5, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (21, N'Seating Alignment', 5, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (22, N'Decorating and Furnishing with Furniture and Glass', 6, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (23, N'Window Coverings', 6, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (24, N'Lightning Effects', 6, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (25, N'Curtain Designing', 6, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (26, N'Architectural design', 6, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (27, N'Planting', 6, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (28, N'Seating Alignment', 6, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (29, N'Decorating and Furnishing with Furniture And Glass', 2, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (30, N'Kitchen Design', 2, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (31, N'Flooring Layout', 2, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (32, N'Window Coverings', 2, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (33, N'Lightning Effects', 2, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (34, N'Colour Schemes', 2, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (35, N'Curtain Designing', 2, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (36, N'Architectural design', 2, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (37, N'Planting', 2, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (38, N'Seating Alignment', 2, N'')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (39, N'Curtain Designing', 4, N'<h1>&nbsp;</h1>

<p>Curtains - We are sure to have a style to suit you</p>

<h2>&nbsp;</h2>

<p>Curtain Design - Providing a mobile service throughout the Waikato and South Auckland regions.&nbsp;</p>

<p>&nbsp;</p>

<p><img alt="" src="http://www.curtainshamilton.co.nz/edit/image_cache/curtainsbanner_1000x400c0pcenter.jpg" /></p>

<p>&nbsp;</p>

<p>Custom Made Curtains to suit your window, colours and style to match your decor as well as quality workmanship and installation. Custom made curtains fit your window perfectly. New Zealanders love to individualise their homes with window shapes and sizes differing from home to home. Creating a need for custom made curtains or drapes.<br />
<br />
While draperies and curtains have long since been noted as being interchangeable phrases for the same items, this is not necessarily the truth. The sashes that are used in decorating and framing a window are typically deemed the curtains within the room, whereas the lengths of fabric that are drawn closed and intended to block light from the outside are typically considered to be the draperies. However, in New Zealand it is typical for all types of drapes and curtains to simply be named as curtains.<br />
Whichever way you choose to name them, utilising the right colours and material can make a huge difference to the overall look and feel of the room. There are many different details that can change the look and feel from the length of the curtain, the pleat type that is chosen and the colour and patterns of the fabric.<br />
&nbsp;</p>

<h2>Curtain Product Showcase. Click On The Boxes Below For More Inspiration</h2>

<h2><a href="http://www.curtainshamilton.co.nz/on-sale" target="_self">On Sale</a></h2>

<p><a href="http://www.curtainshamilton.co.nz/on-sale" target="_self"><img alt="On Sale" src="http://www.curtainshamilton.co.nz/edit/image_cache/orbit_tomato_480x300c1pcenter.jpg" /></a></p>

<p>&nbsp;</p>

<h2><a href="http://www.curtainshamilton.co.nz/galleries-items/neutrals" target="_self">Neutrals</a></h2>

<p><a href="http://www.curtainshamilton.co.nz/galleries-items/neutrals" target="_self"><img alt="Neutrals" src="http://www.curtainshamilton.co.nz/edit/image_cache/ashbourne_moonlight_480x300c1pcenter.jpg" /></a></p>

<p>&nbsp;</p>

<h2><a href="http://www.curtainshamilton.co.nz/galleries-items/all-others" target="_self">All Others</a></h2>

<p><a href="http://www.curtainshamilton.co.nz/galleries-items/all-others" target="_self"><img alt="All Others" src="http://www.curtainshamilton.co.nz/edit/image_cache/ashbourne_opal_480x300c1pcenter.jpg" /></a></p>

<p>&nbsp;</p>

<h2><a href="http://www.curtainshamilton.co.nz/galleries-items/plain" target="_self">Plain</a></h2>

<p><a href="http://www.curtainshamilton.co.nz/galleries-items/plain" target="_self"><img alt="Plain" src="http://www.curtainshamilton.co.nz/edit/image_cache/kiko_putty_480x300c1pcenter.jpg" /></a></p>

<p>&nbsp;</p>

<h2><a href="http://www.curtainshamilton.co.nz/galleries-items/stripes" target="_self">Stripes</a></h2>

<p><a href="http://www.curtainshamilton.co.nz/galleries-items/stripes" target="_self"><img alt="Stripes" src="http://www.curtainshamilton.co.nz/edit/image_cache/broadway_sisal_480x300c1pcenter.jpg" /></a></p>

<p>&nbsp;</p>

<h2><a href="http://www.curtainshamilton.co.nz/galleries-items/pattern" target="_self">Pattern</a></h2>

<p><a href="http://www.curtainshamilton.co.nz/galleries-items/pattern" target="_self"><img alt="Pattern" src="http://www.curtainshamilton.co.nz/edit/image_cache/tulipa_silver_480x300c1pcenter.jpg" /></a></p>

<p>&nbsp;</p>

<h2><a href="http://www.curtainshamilton.co.nz/galleries-items/sheers" target="_self">Sheers</a></h2>

<p><a href="http://www.curtainshamilton.co.nz/galleries-items/sheers" target="_self"><img alt="Sheers" src="http://www.curtainshamilton.co.nz/edit/image_cache/sienna_ivory_480x300c1pcenter.jpg" /></a></p>

<p>&nbsp;</p>

<h2><a href="http://www.curtainshamilton.co.nz/popular" target="_self">Popular</a></h2>

<p><a href="http://www.curtainshamilton.co.nz/popular" target="_self"><img alt="Popular" src="http://www.curtainshamilton.co.nz/edit/image_cache/kiko_black_plum_480x300c1pcenter.jpg" /></a></p>

<p>&nbsp;</p>

<h2><a href="http://www.curtainshamilton.co.nz/galleries-items/florals" target="_self">Florals</a></h2>

<p><a href="http://www.curtainshamilton.co.nz/galleries-items/florals" target="_self"><img alt="Florals" src="http://www.curtainshamilton.co.nz/edit/image_cache/floral_480x300c1pcenter.jpg" /></a></p>

<h2>Types of Fabrics</h2>

<p><br />
<strong>Polyester</strong><br />
A high percentage of curtain fabric these days have either a portion or are entirely made up of polyester. It is a man made product there for it wears better and is easier to clean. It holds it&#39;s shape and therefore a set length year round is achievable.<br />
<br />
<strong>Linen</strong><br />
Hugely popular at present is a linen look. Full linen curtains pleat fantastically and can look amazing. It&#39;s always recommended to puddle lines to a certain degree as they can shrink and expand from season to season. Being natural again it&#39;s recommended to make sure of your lining and dry-clean commercially.<br />
<br />
<strong>Cotton</strong><br />
A very common fabric that is used a lot in curtains. As a natural fiber it has a fresh clean look to it and works well as both a traditional and a modern style. It is essential to line a cotton with a solid lining. This helps the add weight and therefore let the curtain hang nicer. And also to help blockout the UV light from breaking down the cotton over time. Chose a tighter weave cotton if you are looking to make the room darker.<br />
<br />
<strong>Silk</strong><br />
Not often used but an option nonetheless. When used well it can look stunning in a number of settings particularly in settings where a formal but soft romantic feeling is required. Silk is another natural fiber and therefore susceptible to fading and moisture so it&#39;s well worth investing in a good lining and other options to keep the sun off the face fabric.<br />
<br />
<strong>Velvet</strong><br />
Not as common as they once were velvet&#39;s are still a great choice in the right setting. Whether it&#39;s a true velvet or a polyester look alike. A heavier fabric that depending upon the pleat you choose may require a slightly thinner or separate lining to help with the look.<br />
<br />
<strong>Voile</strong><br />
Voile&#39;s are usually made of polyester with an open weave to allow light through. Used to help with privacy during the day time without loosing the natural light. But also to help lower the amount of UV Light entering the room that can fade carpet and furniture.</p>
')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (40, N'Wood Decoration', 2, N'<h2>WELCOME TO ALLURING DECORATION</h2>

<p>ALLURING Decor Company (ADCO) strives to meet the requirements of high quality craftsmanship specializing in architectural design and finishes, particularly woodwork and parquet flooring. We provide services for both interior and exterior design projects based on our client&rsquo;s needs. We take particular pride in incorporating timeless styles and current trends in our work, along with the client&rsquo;s vision to create a truly unique and bespoke project.<br />
Our reputation in the Kingdom has enabled us to be the sole dealers in Saudi Arabia of some of the most prestigious brands in the world including, BERTI, SIDEL, CHIMIVER and EINWOOD</p>

<h3>WOOD WORKS</h3>

<p>Internal and External Wood custom made wood works starting with the smallest ornamented figure inside your house and ending with the biggest automatic gate</p>

<h3>FLOORING</h3>

<p>Laser-inlaid wood floor. A combination of different species of woods, marbles, steel and brass</p>

<h3>WOOD AND ALUMNINIUM</h3>

<p>Windows, doors and skylight made of composite wood &amp; aluminium profile.</p>

<h3>CLADDING</h3>

<p>Natural Wood Cladding for external facades.</p>

<p><img alt="about-alkayan-decor" src="http://www.alkayandecor.com/wp-content/uploads/2015/02/about-alkayan-decor.jpg" /></p>

<h2>CORPORATE PROFILE</h2>

<p>ALLURING Decor Co. Ltd. provides contracting and custom services to clients requiring high quality craftsmanship in the field of &ldquo;Woodworks.&rdquo; Specializing in architectural design and finishes, including parquet, doors, panels, cabinets, trellises, pergolas, and cabanas, we provide a wide array of products to suit your needs.<br />
Our skilled team of designers and technicians, along with the latest modern technologies allows us to provide services for both exterior and interior design projects based on our client&rsquo;s needs. We take particular pride in incorporating timeless styles and current trends in our work, along with the client&rsquo;s vision to create a truly unique and bespoke project.<br />
Being the leading company in the field of natural laser inlaid parquet and external natural wood cladding, custom made products are a vital part of Al Kayan. Whether it be creating the perfect artistic parquet entryway in your home, a carved ornamental library, or an outdoor bungalow with external wood cladding, we guarantee the highest level of standard and quality.<br />
Our reputation in the Kingdom has enabled us to be the sole dealers in Saudi Arabia of some of the most prestigious brands in the world including, BERTI, SIDEL, TECHNOWOOD , CHIMIVER among others.<br />
ALLURING would be most pleased to welcome you to our family and show you the endless possibilities we can create to make your fantasy become a reality.</p>
')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (41, N'Flooring layout', 4, N'<h2>Floor Laying Services</h2>

<p><img alt="Floor laying company" src="http://www.sdlflooring.com/images/floor-layers.jpg" /></p>

<p>SDL Flooring is a UK leading commercial and domestic floor laying company, we specialise in providing premium quality flooring installations at competitive prices.</p>

<h3>Some of the services we provide;</h3>

<ul>
	<li>Floor preparation</li>
	<li>Floor carpet tiles</li>
	<li>Vinyl / Rubber flooring</li>
	<li>Luxury vinyl tiles</li>
	<li>Safety Flooring</li>
	<li>Heavy duty flooring</li>
</ul>

<p>At SDL Flooring we are fully prepared and experienced for any type of floor laying. All our staff is fully accredited and work to the latest health and safety standards as required by the safe code of work practices. We understand that every project is different and that everyone has their own unique requirement, which is why we will work closely with you to ensure complete customer satisfaction from start to finish. Some of the trades we have provided for;</p>

<ul>
	<li>New build homes / apartments</li>
	<li>Office buildings / call centres</li>
	<li>Warehouse &amp; Industrial spaces</li>
	<li>Outdoor festivals / marketing events</li>
	<li>NHS &amp; council buildings</li>
	<li>Retail sector / Hotels</li>
	<li>Animal &amp; pet welfare</li>
	<li>Educational institutes &amp; schools</li>
</ul>

<p>Visit our gallery and view some of the premium floors we have completed for our clients.</p>
')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (42, N'Kitchen Design', 4, N'<table border="0" cellspacing="5">
	<tbody>
		<tr>
			<td>
			<h1>More than just kitchens&hellip;</h1>

			<p>The heart &amp; hearth of a home is the Kitchen where many great memories are made with family and friends. Trust the design and/or re-imagining of this important space to Kitchen Design Services. We have over 20 years of experience in the industry collaborating with other interior designers, builders &amp; contractors, real estate agents, and homeowners alike to create functional, beautiful, and enduring design solutions.</p>

			<p>Our services range from basic kitchen design and space planning to more extensive and integrated project development.</p>

			<p>We provide:</p>

			<ul>
				<li>Design &amp; Space Planning with CAD perspective drawings and elevations for any rooms needing cabinetry.</li>
				<li>Design &amp; Pricing of Cabinetry, with a selection from the most current, fashionable styles &amp; finishes that can be made to custom specifications. Site measurement &amp; verification.</li>
			</ul>

			<p>&nbsp;</p>

			<p>We also offer a more comprehensive service, Resource Recommendation/Design Build A-la-Carte. Our years of experience enable us to quickly understand any design or project needs. And, because of KDS long-term relationship with many industry-related fields, we are confident to provide our most trusted resources:</p>

			<ul>
				<li>Contractors</li>
				<li>Installers</li>
				<li>Interior Designers</li>
				<li>Title 24 Compliance</li>
				<li>Material Selection &amp; Product Resources</li>
			</ul>
			</td>
			<td>
			<p><a href="http://www.houzz.com/pro/kevinkds/kitchen-design-services"><img alt="badge_13_8" src="http://www.kdskitchens.com//wp-content/uploads/2013/12/badge_13_8.png" style="height:80px; width:80px" /></a></p>
			<img alt="aboutimage2" src="http://www.kdskitchens.com//wp-content/uploads/2013/12/aboutimage2.jpg" style="height:171px; width:148px" /><img alt="aboutimage1" src="http://www.kdskitchens.com//wp-content/uploads/2013/12/aboutimage1.jpg" style="height:171px; width:148px" />
			<p>&nbsp;</p>

			<h2>&nbsp;</h2>

			<p>&nbsp;</p>

			<p>Our Advantages</p>

			<ul>
				<li>20+ years experience</li>
				<li>Knowledge of Construction</li>
				<li>&amp; Installation</li>
				<li>Great working relationship with other industry professionals</li>
				<li>Creative Design Solutions</li>
				<li>Quick design turnaround</li>
				<li>Extensive selection of latest styles</li>
				<li>&amp; finishes</li>
				<li>Full custom capabilities</li>
				<li>Competitive pricing</li>
				<li>Functional &amp; Beautiful results</li>
			</ul>
			</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<h3>&ldquo;We&rsquo;d love the opportunity to share our expertise and help with your project. Call us today!&rdquo;</h3>
')
INSERT [dbo].[ServicesOffered] ([ServicesOfferedID], [Name], [DomainID], [Content]) VALUES (43, N'Architectural Design', 4, N'<p><strong>Our architectural design services for building &amp; construction industry are powered by innovation and customized solutions to fulfill your comprehensive requirements.</strong></p>

<p>Several International Architectural projects are executed by hitech that has carved a niche as one of the leading Architectural Design Service provider.</p>

<p>Our Expertise includes:</p>

<ul>
	<li>Modern Architectural Design</li>
	<li>Concept Development</li>
	<li>Interior, Exterior &amp; Landscape Design</li>
	<li>Furniture</li>
	<li>Bridge Plan Design</li>
	<li>Retail Space Planning</li>
	<li>Resurrection Planning and Designing for Heritage Sites</li>
	<li>Design for Infrastructural Facilities like Airports, Metro Stations, City Centers &amp; many more</li>
</ul>

<p>We strongly believe, that building &amp; construction industry architecture has a major influence on the quality of life of its occupants, may it be residential buildings, commercial complexes, public edifices, government offices or museums.<br />
<img alt="usgbclogo" src="http://www.hitechos.com/wp-content/uploads/2014/09/usgbclogo.jpg" style="height:99px; width:100px" /></p>

<p>Architectural Design Services Offerings</p>

<ul>
	<li>Bidding &amp; Negotiation Phase Services</li>
	<li>Design Phase Services</li>
	<li>Planning Services</li>
	<li>Construction Services</li>
	<li>Construction Documentation</li>
</ul>

<p>Architects at hitech conduct a detailed analysis of client requirements, based on which our projects take off. We rely on a variety of mediums from hand sketches to the use of a variety of architectural design software and tools in order to make the execution process fast and convenient. We help you visualize your project site replete with its surroundings and architecture by creating effective photomontages.</p>

<p>We go to great extents to recognize the purpose and use of the architectural spaces we design &amp; give due importance to the cultural and social considerations of the people who will use them. We truly believe in embracing sustainability, hence energy efficiency and green designs remain at the core.</p>

<p>We take pride in creating reliable designs that are inspiring, practical and devoted to improving the natural and built environment. The relevant experience and expertise of our team in building designs and architecture reflects in the way we collaborate and work with our clients, engineers and consultants.</p>
')
SET IDENTITY_INSERT [dbo].[ServicesOffered] OFF
SET IDENTITY_INSERT [dbo].[ServicesRequestStatus] ON 

INSERT [dbo].[ServicesRequestStatus] ([ServicesRequestStatusID], [Name]) VALUES (1, N'On-Going')
INSERT [dbo].[ServicesRequestStatus] ([ServicesRequestStatusID], [Name]) VALUES (2, N'Up-Coming')
INSERT [dbo].[ServicesRequestStatus] ([ServicesRequestStatusID], [Name]) VALUES (3, N'Accomplished')
INSERT [dbo].[ServicesRequestStatus] ([ServicesRequestStatusID], [Name]) VALUES (4, N'Cancelled')
SET IDENTITY_INSERT [dbo].[ServicesRequestStatus] OFF
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [Address], [Phone]) VALUES (1, N'Bao', N'Nguyen', N'thaibao@gmail.com', N'LVH Street', N'01920912312')
INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [Address], [Phone]) VALUES (11, N'Bao G', N'Nguyen G', N'g@gmail.com', N'g address', N'12341414141')
INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [Address], [Phone]) VALUES (13, N'Bao', N'Nguyen', N'thaibao', N'asdasd', N'asd')
INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [Address], [Phone]) VALUES (14, N'guest', N'guest', N'guest@gmail.com', N'Unknown', N'01910192029')
INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [Address], [Phone]) VALUES (15, N'firstname', N'lastname', N'clien111t@gmail.com', N'client', N'123123123213')
INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [Address], [Phone]) VALUES (1014, N'zzxcxz', N'czxcxz', N'czx', N'czxc', N'1231231')
INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [Address], [Phone]) VALUES (1015, N'zxc', N'zxc', N'zxczx', N'czxc', N'12312321312')
INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [Address], [Phone]) VALUES (1016, N'213123', N'zxc123123', N'zxczx123', N'czxc3', N'1231233123')
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
