program AgendaMedica;

uses
  System.StartUpCopy,
  FMX.Forms,
  umain in 'umain.pas' {Form5};

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TForm5, Form5);
  Application.Run;
end.
