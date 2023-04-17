import { render, screen, fireEvent } from '@testing-library/react';
import Note from './Note';

describe('Note component', () => {
  const mockNote = {
    id: '123',
    content: 'This is a test note',
    handleDeleteNote: jest.fn(),
  };

  beforeEach(() => {
    jest.clearAllMocks();
  });

  it('renders the note content', () => {
    render(<Note {...mockNote} />);
    const noteContent = screen.getByText(mockNote.content);
    expect(noteContent).toBeInTheDocument();
  });

  it('calls handleDeleteNote when the delete button is clicked', () => {
    render(<Note {...mockNote} />);
    const deleteButton = screen.getByLabelText(`delete-note-${mockNote.id}`);
    fireEvent.click(deleteButton);
    expect(mockNote.handleDeleteNote).toHaveBeenCalledWith(mockNote.id);
  });

  it('navigates to the edit note page when the edit button is clicked', () => {
    const mockWindowLocation = { href: '' };
    delete window.location;
    window.location = mockWindowLocation;
    render(<Note {...mockNote} />);
    const editButton = screen.getByRole('button', { name: /edit/i });
    fireEvent.click(editButton);
    expect(mockWindowLocation.href).toEqual(`edit/${mockNote.id}`);
  });
});
