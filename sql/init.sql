USE [HQ_BLOG]
GO
/****** Object:  Table [dbo].[HQ_USER]    Script Date: 11/10/2022 09:29:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HQ_USER](
	[ID] [nvarchar](255) NOT NULL PRIMARY KEY,
	[EMAIL] [nvarchar](255) NULL,
	[FULLNAME] [nvarchar](255) NULL,
	[STATUS] [int] NULL,
	[UPDATED_AT] [datetime2](7) NULL,
	[USERNAME] [nvarchar](255) NULL,
	[PASSWORD] [nvarchar](255) NULL,
	[CREATED_AT] [datetime2](7) NULL,
	[DELETED_AT] [datetime2](7) NULL,
)
GO
/****** Object:  Table [dbo].[HQ_POSTS]    Script Date: 11/10/2022 09:29:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].HQ_POSTS(
	[ID] [nvarchar](255) NOT NULL PRIMARY KEY,
	[USER_ID] [nvarchar](255) NULL,
	[SLUG] [nvarchar](500) NULL,
	[TITLE] [nvarchar](100) NULL,
	[IMAGE] [nvarchar](500) NULL,
	[STATUS] [int] NULL,
	[PUBLISHED] [bit] null,
	[UPDATED_AT] [datetime2](7) NULL,
	[CREATED_AT] [datetime2](7) NULL,
)
GO