USE [horas_sistemcobro]
GO
/****** Object:  Schema [horasproyecto]    Script Date: 22/05/2019 11:08:38 a. m. ******/
CREATE SCHEMA [horasproyecto]
GO
/****** Object:  Table [horasproyecto].[campana]    Script Date: 22/05/2019 11:08:38 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [horasproyecto].[campana](
	[idcampana] [int] IDENTITY(1,1) NOT NULL,
	[detalle] [varchar](150) NOT NULL,
	[idusuariocrea] [int] NOT NULL,
	[fechacrea] [datetime] NOT NULL,
	[idusuariomod] [int] NULL,
	[fechamod] [datetime] NULL,
	[estado] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idcampana] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [horasproyecto].[desarrollador]    Script Date: 22/05/2019 11:08:38 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [horasproyecto].[desarrollador](
	[iddesarrollador] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](150) NOT NULL,
	[idusuariocrea] [int] NOT NULL,
	[fechacrea] [datetime] NOT NULL,
	[idusuariomod] [int] NULL,
	[fechamod] [datetime] NULL,
	[estado] [int] NOT NULL,
	[codigousuario] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[iddesarrollador] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [horasproyecto].[estado_proyecto]    Script Date: 22/05/2019 11:08:38 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [horasproyecto].[estado_proyecto](
	[idestadoproyecto] [int] IDENTITY(1,1) NOT NULL,
	[detalle] [varchar](150) NOT NULL,
	[idusuariocrea] [int] NOT NULL,
	[fechacrea] [datetime] NOT NULL,
	[idusuariomod] [int] NULL,
	[fechamod] [datetime] NULL,
	[estado] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idestadoproyecto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [horasproyecto].[hora_proyecto]    Script Date: 22/05/2019 11:08:38 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [horasproyecto].[hora_proyecto](
	[idhoraproyecto] [int] IDENTITY(1,1) NOT NULL,
	[idproyecto] [int] NOT NULL,
	[nombrepersona] [varchar](150) NOT NULL,
	[actividad] [varchar](300) NOT NULL,
	[fecha] [date] NOT NULL,
	[horas] [int] NOT NULL,
	[idestadoproyecto] [int] NOT NULL,
	[idusuariocrea] [int] NOT NULL,
	[fechacrea] [datetime] NOT NULL,
	[idusuariomod] [int] NULL,
	[fechamod] [datetime] NULL,
	[estado] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idhoraproyecto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [horasproyecto].[linea_negocio]    Script Date: 22/05/2019 11:08:38 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [horasproyecto].[linea_negocio](
	[idlineanegocio] [int] IDENTITY(1,1) NOT NULL,
	[detalle] [varchar](150) NOT NULL,
	[idusuariocrea] [int] NOT NULL,
	[fechacrea] [datetime] NOT NULL,
	[idusuariomod] [int] NULL,
	[fechamod] [datetime] NULL,
	[estado] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idlineanegocio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [horasproyecto].[proyecto]    Script Date: 22/05/2019 11:08:38 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [horasproyecto].[proyecto](
	[idproyecto] [int] IDENTITY(1,1) NOT NULL,
	[priorizacioncomite] [varchar](5) NOT NULL,
	[nombresolicitante] [varchar](150) NOT NULL,
	[idtipoproyecto] [int] NOT NULL,
	[idcampana] [int] NOT NULL,
	[idlineanegocio] [int] NOT NULL,
	[nombreproyecto] [varchar](150) NOT NULL,
	[detalleproyecto] [varchar](300) NOT NULL,
	[iddesarrollador] [int] NOT NULL,
	[idestadoproyecto] [int] NOT NULL,
	[fechainicioproyecto] [date] NOT NULL,
	[fechafinproyecto] [date] NULL,
	[idusuariocrea] [int] NOT NULL,
	[fechacrea] [datetime] NOT NULL,
	[idusuariomod] [int] NULL,
	[fechamod] [datetime] NULL,
	[estado] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idproyecto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [horasproyecto].[tipo_proyecto]    Script Date: 22/05/2019 11:08:38 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [horasproyecto].[tipo_proyecto](
	[idtipoproyecto] [int] IDENTITY(1,1) NOT NULL,
	[detalle] [varchar](150) NOT NULL,
	[idusuariocrea] [int] NOT NULL,
	[fechacrea] [datetime] NOT NULL,
	[idusuariomod] [int] NULL,
	[fechamod] [datetime] NULL,
	[estado] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idtipoproyecto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [horasproyecto].[campana] ON 

INSERT [horasproyecto].[campana] ([idcampana], [detalle], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (1, N'NPL', 9011756, CAST(N'2019-05-15T11:10:41.533' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[campana] ([idcampana], [detalle], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (2, N'Bancoagrario', 9011756, CAST(N'2019-05-15T11:10:45.470' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[campana] ([idcampana], [detalle], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (3, N'Daviplata', 9011756, CAST(N'2019-05-15T11:23:08.990' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[campana] ([idcampana], [detalle], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (4, N'Farmatodo', 9011756, CAST(N'2019-05-15T11:24:13.383' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[campana] ([idcampana], [detalle], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (5, N'CRM Perú', 9011756, CAST(N'2019-05-15T13:23:12.653' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[campana] ([idcampana], [detalle], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (6, N'Locatel', 9009900, CAST(N'2019-05-18T11:07:28.967' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[campana] ([idcampana], [detalle], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (7, N'Daviplata', 9009900, CAST(N'2019-05-22T07:37:08.710' AS DateTime), NULL, NULL, 2)
SET IDENTITY_INSERT [horasproyecto].[campana] OFF
SET IDENTITY_INSERT [horasproyecto].[desarrollador] ON 

INSERT [horasproyecto].[desarrollador] ([iddesarrollador], [nombre], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado], [codigousuario]) VALUES (1, N'Camilo Ochoa', 9011756, CAST(N'2019-05-15T11:35:19.663' AS DateTime), NULL, NULL, 2, N'9009900')
INSERT [horasproyecto].[desarrollador] ([iddesarrollador], [nombre], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado], [codigousuario]) VALUES (2, N'Diego Gutierrez', 9009900, CAST(N'2019-05-18T10:21:04.320' AS DateTime), NULL, NULL, 2, N'9010923')
INSERT [horasproyecto].[desarrollador] ([iddesarrollador], [nombre], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado], [codigousuario]) VALUES (3, N'Cristian Aguirre', 9009900, CAST(N'2019-05-22T07:38:17.163' AS DateTime), NULL, NULL, 2, N'9009904')
SET IDENTITY_INSERT [horasproyecto].[desarrollador] OFF
SET IDENTITY_INSERT [horasproyecto].[estado_proyecto] ON 

INSERT [horasproyecto].[estado_proyecto] ([idestadoproyecto], [detalle], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (1, N'Desarrollo', 9011756, CAST(N'2019-05-15T11:44:26.367' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[estado_proyecto] ([idestadoproyecto], [detalle], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (2, N'Analisis', 9009900, CAST(N'2019-05-22T07:38:38.727' AS DateTime), NULL, NULL, 2)
SET IDENTITY_INSERT [horasproyecto].[estado_proyecto] OFF
SET IDENTITY_INSERT [horasproyecto].[hora_proyecto] ON 

INSERT [horasproyecto].[hora_proyecto] ([idhoraproyecto], [idproyecto], [nombrepersona], [actividad], [fecha], [horas], [idestadoproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (1, 1, N'Camilo Andres Ochoa Parra', N'se crea modulo', CAST(N'2019-05-15' AS Date), 8, 1, 9011756, CAST(N'2019-05-15T14:09:25.647' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[hora_proyecto] ([idhoraproyecto], [idproyecto], [nombrepersona], [actividad], [fecha], [horas], [idestadoproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (2, 1, N'Camilo Andres Ochoa Parra', N'pruebas', CAST(N'2019-05-16' AS Date), 8, 1, 9011756, CAST(N'2019-05-16T08:20:45.110' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[hora_proyecto] ([idhoraproyecto], [idproyecto], [nombrepersona], [actividad], [fecha], [horas], [idestadoproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (3, 1, N'Camilo Andres Ochoa Parra', N'Monitoreo', CAST(N'2019-05-17' AS Date), 8, 1, 9009900, CAST(N'2019-05-18T10:05:21.780' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[hora_proyecto] ([idhoraproyecto], [idproyecto], [nombrepersona], [actividad], [fecha], [horas], [idestadoproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (4, 1, N'Camilo Andres Ochoa Parra', N'soporte', CAST(N'2019-05-18' AS Date), 2, 1, 9009900, CAST(N'2019-05-18T10:06:34.453' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[hora_proyecto] ([idhoraproyecto], [idproyecto], [nombrepersona], [actividad], [fecha], [horas], [idestadoproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (5, 1, N'Camilo Andres Ochoa Parra', N'prueba', CAST(N'2019-05-18' AS Date), 1, 1, 9009900, CAST(N'2019-05-18T10:07:09.737' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[hora_proyecto] ([idhoraproyecto], [idproyecto], [nombrepersona], [actividad], [fecha], [horas], [idestadoproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (6, 2, N'Diego Fernando Gutierrez Salinas', N'soporte', CAST(N'2019-05-18' AS Date), 4, 1, 9010923, CAST(N'2019-05-18T11:09:06.847' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[hora_proyecto] ([idhoraproyecto], [idproyecto], [nombrepersona], [actividad], [fecha], [horas], [idestadoproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (7, 2, N'Diego Fernando Gutierrez Salinas', N'pruebas', CAST(N'2019-05-20' AS Date), 6, 1, 9010923, CAST(N'2019-05-20T11:07:20.387' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[hora_proyecto] ([idhoraproyecto], [idproyecto], [nombrepersona], [actividad], [fecha], [horas], [idestadoproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (8, 11, N'Camilo Andres Ochoa Parra', N'AmbientaciÃ³n', CAST(N'2019-05-20' AS Date), 8, 1, 9009900, CAST(N'2019-05-20T11:10:38.283' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[hora_proyecto] ([idhoraproyecto], [idproyecto], [nombrepersona], [actividad], [fecha], [horas], [idestadoproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (9, 2, N'Diego Fernando Gutierrez Salinas', N'vagancia', CAST(N'2019-05-22' AS Date), 8, 1, 9010923, CAST(N'2019-05-22T07:36:00.580' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[hora_proyecto] ([idhoraproyecto], [idproyecto], [nombrepersona], [actividad], [fecha], [horas], [idestadoproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (10, 12, N'Cristian Camilo Aguirre Gomez', N'vagancia', CAST(N'2019-05-22' AS Date), 8, 2, 9009904, CAST(N'2019-05-22T07:41:36.170' AS DateTime), NULL, NULL, 2)
SET IDENTITY_INSERT [horasproyecto].[hora_proyecto] OFF
SET IDENTITY_INSERT [horasproyecto].[linea_negocio] ON 

INSERT [horasproyecto].[linea_negocio] ([idlineanegocio], [detalle], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (1, N'ACC', 9011756, CAST(N'2019-05-15T11:46:25.413' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[linea_negocio] ([idlineanegocio], [detalle], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (2, N'BPS', 9009900, CAST(N'2019-05-22T07:39:23.120' AS DateTime), NULL, NULL, 2)
SET IDENTITY_INSERT [horasproyecto].[linea_negocio] OFF
SET IDENTITY_INSERT [horasproyecto].[proyecto] ON 

INSERT [horasproyecto].[proyecto] ([idproyecto], [priorizacioncomite], [nombresolicitante], [idtipoproyecto], [idcampana], [idlineanegocio], [nombreproyecto], [detalleproyecto], [iddesarrollador], [idestadoproyecto], [fechainicioproyecto], [fechafinproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (1, N'SI', N'Carlo Zegarra', 1, 5, 1, N'Mi Banco', N'Mi Banco CRM', 1, 1, CAST(N'2019-05-15' AS Date), CAST(N'2019-05-29' AS Date), 9011756, CAST(N'2019-05-15T13:39:51.367' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[proyecto] ([idproyecto], [priorizacioncomite], [nombresolicitante], [idtipoproyecto], [idcampana], [idlineanegocio], [nombreproyecto], [detalleproyecto], [iddesarrollador], [idestadoproyecto], [fechainicioproyecto], [fechafinproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (2, N'SI', N'Carlos', 1, 6, 1, N'Locatel', N'Locatel CRM Colombia', 2, 1, CAST(N'2019-04-02' AS Date), CAST(N'2019-06-27' AS Date), 9009900, CAST(N'2019-05-18T11:08:31.547' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[proyecto] ([idproyecto], [priorizacioncomite], [nombresolicitante], [idtipoproyecto], [idcampana], [idlineanegocio], [nombreproyecto], [detalleproyecto], [iddesarrollador], [idestadoproyecto], [fechainicioproyecto], [fechafinproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (10, N'SI', N'Carlos', 1, 6, 1, N'Equidad', N'Equidad', 2, 1, CAST(N'2019-05-17' AS Date), CAST(N'2019-05-23' AS Date), 9010923, CAST(N'2019-05-18T11:14:36.243' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[proyecto] ([idproyecto], [priorizacioncomite], [nombresolicitante], [idtipoproyecto], [idcampana], [idlineanegocio], [nombreproyecto], [detalleproyecto], [iddesarrollador], [idestadoproyecto], [fechainicioproyecto], [fechafinproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (11, N'SI', N'Carlos', 1, 1, 1, N'carterasocio', N'carterasocio comites ', 1, 1, CAST(N'2019-05-20' AS Date), CAST(N'2019-05-24' AS Date), 9009900, CAST(N'2019-05-20T11:09:10.140' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[proyecto] ([idproyecto], [priorizacioncomite], [nombresolicitante], [idtipoproyecto], [idcampana], [idlineanegocio], [nombreproyecto], [detalleproyecto], [iddesarrollador], [idestadoproyecto], [fechainicioproyecto], [fechafinproyecto], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (12, N'NO', N'Julia', 2, 3, 2, N'Daviplata nimitz', N'proyecto chat boot', 3, 2, CAST(N'2019-05-20' AS Date), CAST(N'2019-05-31' AS Date), 9009900, CAST(N'2019-05-22T07:40:14.573' AS DateTime), NULL, NULL, 2)
SET IDENTITY_INSERT [horasproyecto].[proyecto] OFF
SET IDENTITY_INSERT [horasproyecto].[tipo_proyecto] ON 

INSERT [horasproyecto].[tipo_proyecto] ([idtipoproyecto], [detalle], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (1, N'Nuevo Requerimiento', 9011756, CAST(N'2019-05-15T11:53:16.833' AS DateTime), NULL, NULL, 2)
INSERT [horasproyecto].[tipo_proyecto] ([idtipoproyecto], [detalle], [idusuariocrea], [fechacrea], [idusuariomod], [fechamod], [estado]) VALUES (2, N'Mejora', 9009900, CAST(N'2019-05-22T07:38:52.960' AS DateTime), NULL, NULL, 2)
SET IDENTITY_INSERT [horasproyecto].[tipo_proyecto] OFF
ALTER TABLE [horasproyecto].[hora_proyecto]  WITH CHECK ADD FOREIGN KEY([idestadoproyecto])
REFERENCES [horasproyecto].[estado_proyecto] ([idestadoproyecto])
GO
ALTER TABLE [horasproyecto].[hora_proyecto]  WITH CHECK ADD FOREIGN KEY([idproyecto])
REFERENCES [horasproyecto].[proyecto] ([idproyecto])
GO
ALTER TABLE [horasproyecto].[proyecto]  WITH CHECK ADD FOREIGN KEY([idcampana])
REFERENCES [horasproyecto].[campana] ([idcampana])
GO
ALTER TABLE [horasproyecto].[proyecto]  WITH CHECK ADD FOREIGN KEY([iddesarrollador])
REFERENCES [horasproyecto].[desarrollador] ([iddesarrollador])
GO
ALTER TABLE [horasproyecto].[proyecto]  WITH CHECK ADD FOREIGN KEY([idestadoproyecto])
REFERENCES [horasproyecto].[estado_proyecto] ([idestadoproyecto])
GO
ALTER TABLE [horasproyecto].[proyecto]  WITH CHECK ADD FOREIGN KEY([idlineanegocio])
REFERENCES [horasproyecto].[linea_negocio] ([idlineanegocio])
GO
ALTER TABLE [horasproyecto].[proyecto]  WITH CHECK ADD FOREIGN KEY([idtipoproyecto])
REFERENCES [horasproyecto].[tipo_proyecto] ([idtipoproyecto])
GO
